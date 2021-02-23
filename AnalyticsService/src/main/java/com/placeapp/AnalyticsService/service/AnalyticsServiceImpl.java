package com.placeapp.AnalyticsService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placeapp.AnalyticsService.model.AnalyticsModel;
import com.placeapp.AnalyticsService.repository.AnalyticsRepo;

@Service
public class AnalyticsServiceImpl implements AnalyticsService{
	
	@Autowired
	AnalyticsRepo repo;

	@Override
	public AnalyticsModel incrementCounter(AnalyticsModel analyticsObj) {
		AnalyticsModel updatedCounterJob = null;
		AnalyticsModel jobExists = findJobById(analyticsObj.getJobId());
		
		if(jobExists==null) {
			
			if(analyticsObj!=null) {
				int result = analyticsObj.getCounter();
				result++;
				analyticsObj.setCounter(result);
				updatedCounterJob = repo.save(analyticsObj);
			}
			
			
		}
		else {
			int existingCounter = jobExists.getCounter();
			existingCounter++;
			jobExists.setCounter(existingCounter);
			updatedCounterJob = repo.save(jobExists);
			
			
			
			
		}
		return updatedCounterJob;
		
	}

	@Override
	public List<AnalyticsModel> viewAllJobCounters() {
		List<AnalyticsModel> jobCounterList = repo.findAll();
		return jobCounterList;
	}

	@Override
	public AnalyticsModel findJobById(int jobId) {
		Optional<AnalyticsModel> targetJob = repo.findById(jobId);
		if(targetJob.isPresent())
			return targetJob.get();
		return null;
		
	}

}
