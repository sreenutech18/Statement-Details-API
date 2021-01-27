package com.citibank.rewards.statement.model;

import lombok.Data;

@Data
public class CustomerContext {
	
	private String cardNum;
	private String cvvNum;
	private String nameOnCard;
	private String expdate; 
	private DateRange dateRange;
	
	

}
