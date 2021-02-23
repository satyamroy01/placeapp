package com.placeapp.FavouriteService.model;

public class FavJob {
	
	private int favJobId;
	private String favJobName;
	private String favJobDesc;
	private String favJobLocation;
	private String favJobPostLevel;
	private String favJobCompany;
	
	
	public FavJob() {
		
	}
	
	
	public FavJob(int favJobId, String favJobName, String favJobDesc, String favJobLocation, String favJobPostLevel,
			String favJobCompany) {
		
		this.favJobId = favJobId;
		this.favJobName = favJobName;
		this.favJobDesc = favJobDesc;
		this.favJobLocation = favJobLocation;
		this.favJobPostLevel = favJobPostLevel;
		this.favJobCompany = favJobCompany;
	}
	
	
	public int getFavJobId() {
		return favJobId;
	}
	public void setFavJobId(int favJobId) {
		this.favJobId = favJobId;
	}
	public String getFavJobName() {
		return favJobName;
	}
	public void setFavJobName(String favJobName) {
		this.favJobName = favJobName;
	}
	public String getFavJobDesc() {
		return favJobDesc;
	}
	public void setFavJobDesc(String favJobDesc) {
		this.favJobDesc = favJobDesc;
	}
	public String getFavJobLocation() {
		return favJobLocation;
	}
	public void setFavJobLocation(String favJobLocation) {
		this.favJobLocation = favJobLocation;
	}
	public String getFavJobPostLevel() {
		return favJobPostLevel;
	}
	public void setFavJobPostLevel(String favJobPostLevel) {
		this.favJobPostLevel = favJobPostLevel;
	}
	public String getFavJobCompany() {
		return favJobCompany;
	}
	public void setFavJobCompany(String favJobCompany) {
		this.favJobCompany = favJobCompany;
	}


	@Override
	public String toString() {
		return "FavJob [favJobId=" + favJobId + ", favJobName=" + favJobName + ", favJobDesc=" + favJobDesc
				+ ", favJobLocation=" + favJobLocation + ", favJobPostLevel=" + favJobPostLevel + ", favJobCompany="
				+ favJobCompany + "]";
	}
	
	
	

}
