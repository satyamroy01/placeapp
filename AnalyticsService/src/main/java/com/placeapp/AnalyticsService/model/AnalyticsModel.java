package com.placeapp.AnalyticsService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AnalyticsModel {
	
	@Id
	private int jobId;
	private int counter;
	
	
	
	public AnalyticsModel() {
	
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public AnalyticsModel(int jobId, int counter) {
		
		this.jobId = jobId;
		this.counter = counter;
	}
	@Override
	public String toString() {
		return "AnalyticsModel [jobId=" + jobId + ", counter=" + counter + "]";
	}
	
	
	

}
