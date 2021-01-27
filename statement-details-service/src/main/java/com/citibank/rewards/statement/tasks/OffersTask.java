package com.citibank.rewards.statement.tasks;

import java.util.concurrent.Callable;

import com.citibank.rewards.offers.service.client.OfferServiceClient;
import com.citibank.rewards.offers.service.client.OfferServiceClientImpl;
import com.citibank.rewards.statement.model.OffersRequest;
import com.citibank.rewards.statement.model.OffersResponse;
import com.citibank.rewards.statement.model.TaskResult;

public class OffersTask implements Callable<TaskResult> {

	private OffersRequest request;

	public OffersTask(OffersRequest request) {
		this.request = request;
	}

	public TaskResult call() throws Exception {

		System.out.println("Entered into offers task :");

		OfferServiceClient svcClient = new OfferServiceClientImpl();

		OffersResponse response = svcClient.getOffers(request);

		TaskResult result = new TaskResult();
		result.setTaskName("offersTask");
		result.setResult(response);
		System.out.println("Exit from offers task :");
		return result;
	}

}
