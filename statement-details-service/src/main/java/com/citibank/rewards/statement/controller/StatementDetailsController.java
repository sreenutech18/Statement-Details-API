package com.citibank.rewards.statement.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citibank.rewards.statement.exception.StatementRequestDataValidationException;
import com.citibank.rewards.statement.model.StatementRequest;
import com.citibank.rewards.statement.model.StatementResponse;
import com.citibank.rewards.statement.service.StatementService;
import com.citibank.rewards.statement.service.StatementServiceImpl;
import com.citibank.rewards.statement.validator.StatementDetailsValidator;

@RestController
public class StatementDetailsController {

	// @PostMapping(value="/statements")
	@RequestMapping(value = "/statements", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	StatementResponse getStatementDetails(
			@RequestBody StatementRequest statementRequest) throws StatementRequestDataValidationException, InterruptedException, ExecutionException {

        //1. Get the request from consumer
		// 2. validate the request
        StatementDetailsValidator validator = new StatementDetailsValidator();
        validator.validateRequest(statementRequest);
        
		// 4. call service layer and get the response
        StatementService service = new StatementServiceImpl();
        StatementResponse response = service.getStatementDetails(statementRequest);
        
		// 5. prepare the controller layer response

		return response;

	}

}
