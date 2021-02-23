package com.placeapp.FavouriteService.services;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placeapp.FavouriteService.exception.FavJobAlreadyExistException;
import com.placeapp.FavouriteService.exception.FavJobNotFoundException;
import com.placeapp.FavouriteService.model.FavJob;
import com.placeapp.FavouriteService.model.UserFavJob;
import com.placeapp.FavouriteService.repository.UserFavRepo;

@Service
public class UserFavServiceImpl implements UserFavService {
	
	@Autowired
	UserFavRepo repo;

	@Override
	public boolean addUserFavJob(UserFavJob userFavJob) throws FavJobAlreadyExistException{
		
		try {
			UserFavJob userexists = findUserFavJobs(userFavJob.getUserId());
			if(userexists == null) {//user is adding fav for the first time
				UserFavJob added = repo.save(userFavJob);
				if(added != null) {
					return true;
				}
				else {
					return false;
				}
				
			}
			else { // not the first time; adding favourites to the existing list
				List<FavJob> favjoblist = userexists.getFavJobList();
				
				favjoblist.addAll(userFavJob.getFavJobList());
				userexists.setFavJobList(favjoblist);
				UserFavJob added =repo.save(userexists);
				if(added != null) {
					return true;
				}
				else {
					return false;
				}
			}
			
		}
		catch(Exception e) {
			throw new FavJobAlreadyExistException("Not sure about this exception");
		}
		
		
		
	}

	@Override
	public List<UserFavJob> viewAllUserFavJobs() {
		List<UserFavJob> userfavList = repo.findAll();
		return userfavList;
	}

	@Override
	public UserFavJob findUserFavJobs(String userId) throws FavJobNotFoundException{
		UserFavJob result = null;
		try {
			Optional<UserFavJob> userFound = repo.findById(userId);
			if(userFound.isPresent())
				return userFound.get();
		}
		catch(NoSuchElementException e) {
			throw new FavJobNotFoundException("User does not exists");
			
		}
		
		return null;
	}

	@Override
	public boolean removeFavJob(String userId, int favJobId) throws FavJobNotFoundException {
		try {
			Optional<UserFavJob> userFound = repo.findById(userId);
			if(userFound.isPresent()) {
				UserFavJob target = userFound.get();
				List<FavJob> favjoblist = target.getFavJobList();
				Iterator<FavJob> iterator = favjoblist.iterator();
				while (iterator.hasNext()){
					FavJob targetJob = iterator.next();
					if(targetJob.getFavJobId() == favJobId)
						iterator.remove();
				}
				target.setFavJobList(favjoblist);
				repo.save(target);
				return true;
			}
			else 
				throw new FavJobNotFoundException("Job does not exists");
		}
		catch(NoSuchElementException e) {
			
			throw new FavJobNotFoundException("Job does not exists");
			
			
		}
		
		
	}

	

}
