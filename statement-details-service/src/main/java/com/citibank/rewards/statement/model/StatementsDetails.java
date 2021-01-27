package com.citibank.rewards.statement.model;

import lombok.Data;

@Data
public class StatementsDetails {
	
	private String txnId;
	private String txnname;
	private String desc;
	private float amount;
	private String merchantName;
	private float redeemPoints;
	private String remarks;

}
