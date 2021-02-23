package com.placeapp.AnalyticsService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.placeapp.AnalyticsService.model.AnalyticsModel;
import com.placeapp.AnalyticsService.service.AnalyticsService;

@CrossOrigin
@RestController
public class AnalyticsController {
	
	@Autowired
	AnalyticsService service;
	
	@PostMapping("/incrementCounter")
	public ResponseEntity<?> incrementJobCounterData(@RequestBody() AnalyticsModel analytics)
	{
		AnalyticsModel updateCounter = service.incrementCounter(analytics);
		return new ResponseEntity<AnalyticsModel>(updateCounter,HttpStatus.OK);
	}
	
	@GetMapping("/showAllAnalytics")
	public ResponseEntity<?> showAllAnalyticsData()
	{
		List<AnalyticsModel> analyticsList=service.viewAllJobCounters();
		
		return new ResponseEntity<List>(analyticsList,HttpStatus.OK);
	}

}
