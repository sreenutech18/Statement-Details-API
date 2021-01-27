package com.citibank.rewards.statement.model;

import lombok.Data;

@Data
public class StatementDaoRequest {
	
	private String cardNum;
	private String cvv;
	private String nameOnCard;
	private String expDate;
	private String clientId;
	

}
