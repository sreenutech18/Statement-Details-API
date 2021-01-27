package com.citibank.rewards.statement.model;

import java.util.List;

import lombok.Data;

@Data
public class OffersResponse {
	
	private StatusBlock statusBlock;
	private List<OffersDetails> offersDtls;
	
	
	

}
