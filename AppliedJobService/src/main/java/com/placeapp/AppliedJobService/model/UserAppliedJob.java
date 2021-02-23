package com.placeapp.AppliedJobService.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserAppliedJob {
	@Id
	private String userId;
	private String userName;
	private List<AppliedJobs> AppliedJobsList;
	
	public UserAppliedJob() {
		
	}
	
	/*
	 * public UserAppliedJob(String userId, String userName, List<AppliedJobs>
	 * appliedJobsList) { this.userId = userId; this.userName = userName;
	 * AppliedJobsList = appliedJobsList; }
	 */
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<AppliedJobs> getAppliedJobsList() {
		return AppliedJobsList;
	}
	public void setAppliedJobsList(List<AppliedJobs> appliedJobsList) {
		AppliedJobsList = appliedJobsList;
	}
	@Override
	public String toString() {
		return "UserAppliedJob [userId=" + userId + ", userName=" + userName + ", AppliedJobsList=" + AppliedJobsList
				+ "]";
	}
	
	

}
