package com.citibank.rewards.statement.tasks;

import java.util.concurrent.Callable;

import com.citibank.rewards.statement.dao.StatementDAO;
import com.citibank.rewards.statement.dao.StatementDAOImpl;
import com.citibank.rewards.statement.model.StatementDaoRequest;
import com.citibank.rewards.statement.model.StatementDaoResponse;
import com.citibank.rewards.statement.model.TaskResult;

public class TransactionTask implements Callable<TaskResult> {

	private StatementDaoRequest daoRequest;
	
	public TransactionTask(StatementDaoRequest daoRequest) {
		this.daoRequest = daoRequest;
	}

	@Override
	public TaskResult call() throws Exception {
		
		System.out.println("Entered into statement task :");
		
		StatementDAO dao = new StatementDAOImpl();

		StatementDaoResponse daoResp = dao.getStatements(daoRequest);

		TaskResult result = new TaskResult();
		result.setResult("transaction");
		result.setResult(daoResp);
		
		System.out.println("Entered from statement task :");
		return result;
	}

}
