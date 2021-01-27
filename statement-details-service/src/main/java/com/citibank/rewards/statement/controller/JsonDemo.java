package com.citibank.rewards.statement.controller;

import com.citibank.rewards.statement.model.ClientContext;
import com.citibank.rewards.statement.model.CustomerContext;
import com.citibank.rewards.statement.model.DateRange;
import com.citibank.rewards.statement.model.StatementRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDemo {

	public static void main(String[] args) throws JsonProcessingException {
		
		StatementRequest statementRequest = new StatementRequest();
		
		ClientContext clientContext = new ClientContext();
		clientContext.setClientId("web");
		clientContext.setRequestId("online");
		clientContext.setMessageTs("abc123xyz");
		
		CustomerContext customerContext = new CustomerContext();
		customerContext.setCardNum("232323232");
		customerContext.setCvvNum("121");
		DateRange dateRange = new DateRange();
		dateRange.setStartDate("01-12-2020");
		dateRange.setEndDate("31-12-2020");
		
		
		customerContext.setDateRange(dateRange);
		customerContext.setExpdate("12/2021");
		customerContext.setNameOnCard("sreenu dandu");
		
		statementRequest.setClientContext(clientContext);
		statementRequest.setCustomerContext(customerContext);
		
		ObjectMapper mapper = new ObjectMapper();
		String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(statementRequest);
		System.out.println("response is :"+response);

	}

}
