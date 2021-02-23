package com.placeapp.AnalyticsService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.placeapp.AnalyticsService.model.AnalyticsModel;

@Repository
public interface AnalyticsRepo extends MongoRepository<AnalyticsModel, Integer>{
	

}
