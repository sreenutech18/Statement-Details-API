package com.citibank.rewards.statement.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.citibank.rewards.statement.model.OffersDetails;
import com.citibank.rewards.statement.model.OffersRequest;
import com.citibank.rewards.statement.model.OffersResponse;
import com.citibank.rewards.statement.model.StatementDaoRequest;
import com.citibank.rewards.statement.model.StatementDaoResponse;
import com.citibank.rewards.statement.model.StatementRequest;
import com.citibank.rewards.statement.model.StatementResponse;
import com.citibank.rewards.statement.model.StatementsDetails;
import com.citibank.rewards.statement.model.StatusBlock;
import com.citibank.rewards.statement.model.TaskResult;
import com.citibank.rewards.statement.model.TransactionInfo;
import com.citibank.rewards.statement.tasks.OffersTask;
import com.citibank.rewards.statement.tasks.TransactionTask;

public class StatementServiceImpl implements StatementService {

	public StatementResponse getStatementDetails(StatementRequest request)
			throws InterruptedException, ExecutionException {

		System.out.println("entered into service layer :" + request);

		StatementResponse response = new StatementResponse();

		ExecutorService service = Executors.newFixedThreadPool(2);

		Set tasks = new HashSet();

		// prepare the request for offers task
		OffersRequest offersRequest = new OffersRequest();
		offersRequest.setCardNum(request.getCustomerContext().getCardNum());
		offersRequest.setClientId(request.getClientContext().getClientId());
		offersRequest.setRequestId(request.getClientContext().getRequestId());

		// prepare the request for statement task
		StatementDaoRequest statementRequest = new StatementDaoRequest();
		statementRequest.setCardNum(request.getCustomerContext().getCardNum());
		statementRequest.setClientId(request.getClientContext().getClientId());
		statementRequest.setCvv(request.getCustomerContext().getCvvNum());
		statementRequest.setExpDate(request.getCustomerContext().getExpdate());
		statementRequest.setNameOnCard(request.getCustomerContext().getNameOnCard());

		tasks.add(new OffersTask(offersRequest));
		tasks.add(new TransactionTask(statementRequest));

		OffersResponse offersResp = null;
		StatementDaoResponse daoResp = null;

		List<TransactionInfo> transactionInfoList = new ArrayList<TransactionInfo>();

		System.out.println("Before invoking parallel calls :");

		List<Future<TaskResult>> futureList = service.invokeAll(tasks);

		for (Future future : futureList) {

			TaskResult taskResult = (TaskResult) future.get();

			if ("offersTask".equals(taskResult.getTaskName())) {

				offersResp = (OffersResponse) taskResult.getResult();

			} else {

				daoResp = (StatementDaoResponse) taskResult.getResult();
			}
			// 1. get the offers resp and transaction resp
			// 2. if offers resp name is available in transaction resp then offerEligible
			// =true else false
		}

		for (StatementsDetails statementDtls : daoResp.getStatementsDetails()) {

			for (OffersDetails offersDetails : offersResp.getOffersDtls()) {

				TransactionInfo transaction = new TransactionInfo();
				transaction.setTxnId(statementDtls.getTxnId());
				transaction.setTxnname(statementDtls.getTxnname());
				transaction.setDesc(statementDtls.getDesc());
				transaction.setMerchantName(statementDtls.getMerchantName());
				transaction.setRedeemPoints(Float.valueOf(statementDtls.getRedeemPoints()));
				transaction.setRemarks(statementDtls.getRemarks());

				/*
				 * if( statementDtls.getTxnname() == null || offersDetails.getOfferName() ==
				 * null ) { transaction.setOfferEligible(false); } else if
				 * (statementDtls.getTxnname().equalsIgnoreCase(offersDetails.getOfferName())) {
				 * transaction.setOfferEligible(true); }else {
				 * transaction.setOfferEligible(false); }
				 */

				if (statementDtls.getTxnname() != null) {

					if (statementDtls.getTxnname().equalsIgnoreCase(offersDetails.getOfferName())) {
						transaction.setOfferEligible(true);
					}

				}

				transactionInfoList.add(transaction);

			}

		}
		
		StatusBlock statusBlock = new StatusBlock();
		statusBlock.setRespCode(daoResp.getRespCode());
		statusBlock.setRespMsg(daoResp.getRespMsg());
		
		response.setStatusBlock(statusBlock);
		response.setTransactionInfo(transactionInfoList);

		System.out.println("exit from service layer :" + response);
		return response;
	}

}
