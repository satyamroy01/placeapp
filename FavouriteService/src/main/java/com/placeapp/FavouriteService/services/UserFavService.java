package com.placeapp.FavouriteService.services;

import java.util.List;

import com.placeapp.FavouriteService.exception.FavJobAlreadyExistException;
import com.placeapp.FavouriteService.exception.FavJobNotFoundException;
import com.placeapp.FavouriteService.model.UserFavJob;

public interface UserFavService {
	
	boolean addUserFavJob(UserFavJob userFavJob) throws FavJobAlreadyExistException;
	List<UserFavJob> viewAllUserFavJobs();
	UserFavJob findUserFavJobs(String userId) throws FavJobNotFoundException;
	boolean removeFavJob(String userId, int favJobId) throws FavJobNotFoundException;

}
