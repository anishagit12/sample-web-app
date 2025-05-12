package com.microservice.useranalytics.Service;

import java.util.List;

import com.microservice.useranalytics.Entity.UserAnalyticsEntity;

public interface UserAnalyticsService {
	
	public UserAnalyticsEntity saveActivity(String email, String activity);
	public List<UserAnalyticsEntity> getUserActivity(String email);

}
