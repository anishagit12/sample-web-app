package com.microservice.useranalytics.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.useranalytics.Entity.UserAnalyticsEntity;
import com.microservice.useranalytics.Service.UserAnalyticsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class UserAnalyticsController {
	
	private final UserAnalyticsServiceImpl servObj;
	
	public UserAnalyticsController(UserAnalyticsServiceImpl servObj) {
		
		this.servObj = servObj;
	}
	
	@PostMapping("/log")
	public UserAnalyticsEntity logActivity(@RequestParam String email, @RequestParam String activity) {
		
		return servObj.saveActivity(email, activity);
	}
	
	@GetMapping("/email/{email}")
	public List<UserAnalyticsEntity> getUserActivity(@PathVariable String email){
		
		return servObj.getUserActivity(email);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
