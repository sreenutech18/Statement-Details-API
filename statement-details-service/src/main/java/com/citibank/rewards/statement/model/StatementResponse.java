package com.citibank.rewards.statement.model;

import java.util.List;

import lombok.Data;

@Data
public class StatementResponse {
	
	private StatusBlock statusBlock;
	private List<TransactionInfo> transactionInfo;
	
	
	
	

}
