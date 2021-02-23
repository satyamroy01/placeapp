package com.placeapp.FavouriteService.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserFavJob {
	
	@Id
	private String userId;
	private String userName;
	private List<FavJob> favJobList;
	
	
	public UserFavJob(String userId, List<FavJob> favJobList) {
		
		this.userId = userId;
		this.favJobList = favJobList;
	}


	public UserFavJob() {
	
	}


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


	public List<FavJob> getFavJobList() {
		return favJobList;
	}


	public void setFavJobList(List<FavJob> favJobList) {
		this.favJobList = favJobList;
	}


	@Override
	public String toString() {
		return "UserFavJob [userId=" + userId + ", favJobList=" + favJobList + "]";
	}
	
	
	

}
