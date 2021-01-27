package com.citibank.rewards.statement.dao;

import com.citibank.rewards.statement.model.StatementDaoRequest;
import com.citibank.rewards.statement.model.StatementDaoResponse;

public interface StatementDAO {
	
	StatementDaoResponse getStatements(StatementDaoRequest daoRequest);

}
