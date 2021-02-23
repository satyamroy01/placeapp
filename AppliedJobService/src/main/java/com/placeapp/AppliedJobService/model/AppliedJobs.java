package com.placeapp.AppliedJobService.model;

import java.time.LocalDateTime;



public class AppliedJobs {
	
	
	private int appliedJobsId;
	private String appliedJobsName;
	private String appliedJobsDesc;
	private String appliedJobsLocation;
	private String appliedJobsPostLevel;
	private String appliedJobsCompany;
	private String appliedJobsStatus;
	
	private LocalDateTime createdAt;
	
	
	public AppliedJobs(int appliedJobsId, String appliedJobsName, String appliedJobsDesc, String appliedJobsLocation,
			String appliedJobsPostLevel, String appliedJobsCompany, String appliedJobsStatus, LocalDateTime createdAt) {
		this.appliedJobsId = appliedJobsId;
		this.appliedJobsName = appliedJobsName;
		this.appliedJobsDesc = appliedJobsDesc;
		this.appliedJobsLocation = appliedJobsLocation;
		this.appliedJobsPostLevel = appliedJobsPostLevel;
		this.appliedJobsCompany = appliedJobsCompany;
		this.appliedJobsStatus = appliedJobsStatus;
		this.createdAt = createdAt;
	}

	public AppliedJobs() {}
	
	public int getAppliedJobsId() {
		return appliedJobsId;
	}

	public void setAppliedJobsId(int appliedJobsId) {
		this.appliedJobsId = appliedJobsId;
	}

	public String getAppliedJobsName() {
		return appliedJobsName;
	}

	public void setAppliedJobsName(String appliedJobsName) {
		this.appliedJobsName = appliedJobsName;
	}

	public String getAppliedJobsDesc() {
		return appliedJobsDesc;
	}

	public void setAppliedJobsDesc(String appliedJobsDesc) {
		this.appliedJobsDesc = appliedJobsDesc;
	}

	public String getAppliedJobsLocation() {
		return appliedJobsLocation;
	}

	public void setAppliedJobsLocation(String appliedJobsLocation) {
		this.appliedJobsLocation = appliedJobsLocation;
	}

	public String getAppliedJobsPostLevel() {
		return appliedJobsPostLevel;
	}

	public void setAppliedJobsPostLevel(String appliedJobsPostLevel) {
		this.appliedJobsPostLevel = appliedJobsPostLevel;
	}

	public String getAppliedJobsCompany() {
		return appliedJobsCompany;
	}

	public void setAppliedJobsCompany(String appliedJobsCompany) {
		this.appliedJobsCompany = appliedJobsCompany;
	}

	public String getAppliedJobsStatus() {
		return appliedJobsStatus;
	}

	public void setAppliedJobsStatus(String appliedJobsStatus) {
		this.appliedJobsStatus = appliedJobsStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "AppliedJobs [appliedJobsId=" + appliedJobsId + ", appliedJobsName=" + appliedJobsName
				+ ", appliedJobsDesc=" + appliedJobsDesc + ", appliedJobsLocation=" + appliedJobsLocation
				+ ", appliedJobsPostLevel=" + appliedJobsPostLevel + ", appliedJobsCompany=" + appliedJobsCompany
				+ ", appliedJobsStatus=" + appliedJobsStatus + ", createdAt=" + createdAt + "]";
	}
	
	

}
