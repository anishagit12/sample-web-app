package com.microservice.useranalytics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.useranalytics.Entity.UserAnalyticsEntity;

import java.util.List;

@Repository
public interface UserAnalyticsRepository extends JpaRepository<UserAnalyticsEntity, Long>{
	
	List<UserAnalyticsEntity> findByUserEmail(String email);
}
