package com.citibank.rewards.statement.service;

import java.util.concurrent.ExecutionException;

import com.citibank.rewards.statement.model.StatementRequest;
import com.citibank.rewards.statement.model.StatementResponse;

public interface StatementService {
	
	StatementResponse getStatementDetails(StatementRequest request) throws InterruptedException, ExecutionException;

}
