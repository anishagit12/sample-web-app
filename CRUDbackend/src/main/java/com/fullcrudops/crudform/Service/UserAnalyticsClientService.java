package com.fullcrudops.crudform.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import reactor.core.publisher.Mono;

@Service
public class UserAnalyticsClientService {
	
	private final WebClient webClientObj;
	
	public UserAnalyticsClientService(WebClient webClientObj) {
		
		this.webClientObj = webClientObj;
	}
	
	public void logUserActivity(String email, String activity) {
		
		webClientObj.post()
					.uri(uriBuilder -> uriBuilder.path("/log")  //appends path to base URL
												 .queryParam("email", email)
												 .queryParam("activity", activity)
												 .build())
					.retrieve()
					.bodyToMono(Void.class) // No response body expected
					.subscribe();			// Executes the request asynchronously
	}
	
	public Mono<String> getUserActivity(String email) {
		
		return webClientObj.get()
						   .uri("/email/{email}", email)
						   .retrieve()
						   .bodyToMono(String.class);		
		
	}

}
