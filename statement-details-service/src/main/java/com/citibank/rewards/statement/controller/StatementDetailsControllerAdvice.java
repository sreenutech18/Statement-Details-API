package com.citibank.rewards.statement.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.citibank.rewards.statement.exception.BusinessException;
import com.citibank.rewards.statement.exception.StatementRequestDataValidationException;
import com.citibank.rewards.statement.exception.SystemException;
import com.citibank.rewards.statement.model.ErrorResponse;
import com.citibank.rewards.statement.model.StatusBlock;


@RestControllerAdvice
public class StatementDetailsControllerAdvice {
	
	@ExceptionHandler(value = StatementRequestDataValidationException.class)
	public ErrorResponse handleRequestInvalidException(StatementRequestDataValidationException exception) {

		ErrorResponse response = buildErrorResp(exception.getRespCode(), exception.getRespMsg());

		return response;
	}

	@ExceptionHandler(value = BusinessException.class)
	public ErrorResponse handleDataErrors(BusinessException exception) {

		ErrorResponse response = buildErrorResp(exception.getRespCode(), exception.getRespMsg());

		return response;
	}

	@ExceptionHandler(value = SystemException.class)
	public ErrorResponse handleSystemErrors(SystemException exception) {

		ErrorResponse response = buildErrorResp(exception.getRespCode(), exception.getRespMsg());

		return response;
	}

	@ExceptionHandler(value = Exception.class)
	public ErrorResponse handleGenericErrors(Exception exception) {

		exception.printStackTrace();
		
		ErrorResponse response = buildErrorResp("22222", "unknown error from service:" + exception.getMessage());
		
	    return response;
	}

	private ErrorResponse buildErrorResp(String respCode, String respMsg) {
		ErrorResponse response = new ErrorResponse();
		StatusBlock statusBlock = new StatusBlock();
		statusBlock.setRespCode(respCode);
		statusBlock.setRespMsg(respMsg);
		response.setStatusBlock(statusBlock);
		return response;
	}


}
