package com.citibank.rewards.statement.validator;

import com.citibank.rewards.statement.exception.StatementRequestDataValidationException;
import com.citibank.rewards.statement.model.ClientContext;
import com.citibank.rewards.statement.model.CustomerContext;
import com.citibank.rewards.statement.model.StatementRequest;

public class StatementDetailsValidator {

	public void validateRequest(StatementRequest request) throws StatementRequestDataValidationException {

		// validate request obj null scenario
		if (request == null || request.getClientContext() == null || request.getCustomerContext() == null) {

			throw new StatementRequestDataValidationException("stmt100", "invalid request object");
		}

		ClientContext clientContext = request.getClientContext();
		// validate client id

		if (clientContext.getClientId() == null || " ".equals(clientContext.getClientId())) {

			throw new StatementRequestDataValidationException("stmt101", "invalid client id");
		}

		// validate request id
		if (clientContext.getRequestId() == null || " ".equals(clientContext.getRequestId())) {

			throw new StatementRequestDataValidationException("stmt100", "invalid request id");
		}

		CustomerContext customerContext = request.getCustomerContext();
		
		// validate cardnum

		if (customerContext.getCardNum() == null || " ".equals(customerContext.getCardNum())) {

			throw new StatementRequestDataValidationException("stmt100", "invalid cardnumber");
		}

	}

}
