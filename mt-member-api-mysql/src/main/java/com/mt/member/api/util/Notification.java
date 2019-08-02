package com.mt.member.api.util;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.mt.member.api.model.AuthCheckRequestModel;

public class Notification {
	
	private static Notification singleton = new Notification();
	
	public static Notification getInstance() {
        return singleton;
    }
	
	public boolean check(AuthCheckRequestModel authCheckRequestModel) {
		HttpEntity<AuthCheckRequestModel> request = new HttpEntity<>(authCheckRequestModel);
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			return restTemplate.postForEntity("http://localhost:8082/auth/check", request, String.class).getStatusCode().is2xxSuccessful();			
		} catch(Exception e) {
			return false;
		}
	}
}
