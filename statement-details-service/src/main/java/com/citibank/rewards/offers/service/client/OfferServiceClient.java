package com.citibank.rewards.offers.service.client;

import com.citibank.rewards.statement.model.OffersRequest;
import com.citibank.rewards.statement.model.OffersResponse;

public interface OfferServiceClient {
	
	OffersResponse getOffers(OffersRequest request);

}
