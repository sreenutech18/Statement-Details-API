package com.citibank.rewards.statement.exception;

public class StatementRequestDataValidationException extends Exception {
	
	private String respCode;
	private String respMsg;
	
	public StatementRequestDataValidationException(String respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public String getRespCode() {
		return respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	
}
