package com.citibank.rewards.statement.model;

import java.util.List;

import lombok.Data;

@Data
public class StatementDaoResponse {
	
	private String respCode;
	private String respMsg;
	private List<StatementsDetails> statementsDetails;

}
