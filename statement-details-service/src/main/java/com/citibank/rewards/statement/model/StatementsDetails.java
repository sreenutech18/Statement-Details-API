package com.citibank.rewards.statement.model;

import lombok.Data;

@Data
public class StatementsDetails {
	
	private String txnId;
	private String txnname;
	private String desc;
	private String  amount;
	private String merchantName;
	private String redeemPoints;
	private String remarks;

}
