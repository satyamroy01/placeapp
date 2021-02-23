package com.placeapp.AnalyticsService.service;

import java.util.List;

import com.placeapp.AnalyticsService.model.AnalyticsModel;

public interface AnalyticsService {
	
	AnalyticsModel incrementCounter(AnalyticsModel analyticsObj);
	List<AnalyticsModel> viewAllJobCounters();
	AnalyticsModel findJobById(int jobId);

}
