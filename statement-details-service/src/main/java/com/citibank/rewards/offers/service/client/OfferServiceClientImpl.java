package com.citibank.rewards.offers.service.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.citibank.rewards.statement.model.OffersRequest;
import com.citibank.rewards.statement.model.OffersResponse;

public class OfferServiceClientImpl implements OfferServiceClient {

	public OffersResponse getOffers(OffersRequest request) {
		
		System.out.println("Entered into offersservice client :"+request);
		OffersResponse svcResp = null;
		try {
			//String uri = "http://localhost:8080/offers-details-service-1.0-SNAPSHOT/offers/34564654654646";
			
			String env = System.getProperty("env");
			String fileName="properties/service/offerssvc-"+env+".properties";
			System.out.println("env :"+env+"filename :"+fileName);
			Properties properties = new Properties();
			InputStream input = OfferServiceClientImpl.class.getClassLoader()
					.getResourceAsStream(fileName);
			properties.load(input);

			String url = properties.getProperty("service-url");
			
			String uname = properties.getProperty("connection-timeout");
			String pwd = properties.getProperty("read-timeout");
			
			System.out.println("url : "+url);
			

			HttpHeaders headers = new HttpHeaders();
			headers.set("client-Id", request.getClientId());
			headers.set("request-id", request.getRequestId());
			headers.set("msg-ts", request.getMsgts());
			
			HttpEntity entity = new HttpEntity(headers);

			RestTemplate template = new RestTemplate();

			HttpEntity<OffersResponse> response = template.exchange(url.concat(request.getCardNum()), HttpMethod.GET, entity, OffersResponse.class);
			
			svcResp = response.getBody();
		
			System.out.println("Exit from offersservice client :"+svcResp);
			
		} catch (RestClientException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return svcResp;
	}
	
	public static void main(String[] args) {
		
		System.setProperty("env", "dev");
		OfferServiceClientImpl svcImpl = new OfferServiceClientImpl();
		OffersRequest request = new OffersRequest();
		request.setCardNum("2323232323");
		request.setClientId("web");
		request.setRequestId("abc1212");
		request.setMsgts("343434");
		
		OffersResponse response = svcImpl.getOffers(request);
		
		System.out.println("service response is :"+response);
	}

}
