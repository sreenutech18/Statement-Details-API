package com.citibank.rewards.statement.model;

import lombok.Data;

@Data
public class StatementRequest {
	
	private ClientContext clientContext;
	private CustomerContext customerContext;

}
