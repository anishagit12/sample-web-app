package com.microservice.useranalytics.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.useranalytics.Entity.UserAnalyticsEntity;
import com.microservice.useranalytics.Repository.UserAnalyticsRepository;

import java.util.List;

@Service
public class UserAnalyticsServiceImpl {
	
	@Autowired
	private final UserAnalyticsRepository repoObj;
	
	public UserAnalyticsServiceImpl(UserAnalyticsRepository repoObj) {
		
		this.repoObj = repoObj;
	}
	
	public UserAnalyticsEntity saveActivity(String email, String activity) {
		
		UserAnalyticsEntity log = new UserAnalyticsEntity();
		log.setUserEmail(email);
		log.setActivityType(activity);
		log.setTimestamp(LocalDateTime.now());
		
		return repoObj.save(log);
	}
	
	public List<UserAnalyticsEntity> getUserActivity(String email){
		
		return repoObj.findByUserEmail(email);
	}

}
