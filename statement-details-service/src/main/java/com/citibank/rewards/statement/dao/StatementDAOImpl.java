package com.citibank.rewards.statement.dao;

import java.util.ArrayList;
import java.util.List;

import com.citibank.rewards.statement.model.StatementDaoRequest;
import com.citibank.rewards.statement.model.StatementDaoResponse;
import com.citibank.rewards.statement.model.StatementsDetails;
import com.citibank.rewards.statement.model.StatementsDetails;

public class StatementDAOImpl implements StatementDAO {

	public StatementDaoResponse getStatements(StatementDaoRequest daoRequest) {
     
	      System.out.println("Entered into DAO Response :"+daoRequest);	
		// 1. get the request from service
		// 2. prepare the request for db
		// 3. call statement db and get the response
		// 4. validate the response
		// 5. if it is success prepare the dao response else handle the exceptions

		StatementDaoResponse daoResp = new StatementDaoResponse();

		daoResp.setRespCode("0");
		daoResp.setRespMsg("success");
		List<StatementsDetails> statementsDetailsList = new ArrayList<StatementsDetails>();

		StatementsDetails statementsDetails = new StatementsDetails();
		statementsDetails.setTxnId("1111");
		statementsDetails.setTxnname("samsung mobile");
		statementsDetails.setDesc("good");
		statementsDetails.setRedeemPoints(10000);
		statementsDetails.setMerchantName("samsung store");
		statementsDetails.setRemarks("na");

		StatementsDetails statementsDetails1 = new StatementsDetails();
		statementsDetails1.setTxnId("1111");
		statementsDetails.setTxnname("hp laptop");
		statementsDetails1.setDesc("good");
		statementsDetails1.setRedeemPoints(100000);
		statementsDetails1.setMerchantName("amazone");
		statementsDetails1.setRemarks("na");

		StatementsDetails statementsDetails2 = new StatementsDetails();
		statementsDetails2.setTxnId("2222");
		statementsDetails.setTxnname("book");
		statementsDetails2.setDesc("good");
		statementsDetails2.setRedeemPoints(10000);
		statementsDetails2.setMerchantName("flipkart");
		statementsDetails2.setRemarks("na");
		
		StatementsDetails statementsDetails3 = new StatementsDetails();
		statementsDetails3.setTxnId("2222");
		statementsDetails.setTxnname("fastrack watch");
		statementsDetails3.setDesc("good");
		statementsDetails3.setRedeemPoints(10000);
		statementsDetails3.setMerchantName("flipkart");
		statementsDetails3.setRemarks("na");

		statementsDetailsList.add(statementsDetails);
		statementsDetailsList.add(statementsDetails1);
		statementsDetailsList.add(statementsDetails2);
		statementsDetailsList.add(statementsDetails3);

		daoResp.setStatementsDetails(statementsDetailsList);

		 System.out.println("Exit  from  DAO Response :"+daoResp);	
		 
		return daoResp;
	}

}
