package com.placeapp.AppliedJobService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placeapp.AppliedJobService.Exception.AppliedJobAlreadyExistException;
import com.placeapp.AppliedJobService.Exception.AppliedJobNotFoundException;
import com.placeapp.AppliedJobService.Repository.UserAppliedJobsRepo;
import com.placeapp.AppliedJobService.model.AppliedJobs;
import com.placeapp.AppliedJobService.model.UserAppliedJob;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class UserAppliedJobServiceImpl implements UserAppliedJobService{

	@Autowired
	UserAppliedJobsRepo Repo;
	
public boolean addUserAppliedJobs(UserAppliedJob userAppliedJob) {
		//throws AppliedJobAlreadyExistException{
	
	//try {
	try {
			UserAppliedJob userexists = findUserAppliedJobs(userAppliedJob.getUserId());
		
			if(userexists == null)
			{
					UserAppliedJob added = Repo.save(userAppliedJob);
					if(added != null) 
					{
							return true;
					}
					else 
					{
						return false;
					}	
			}
			else
			{
				
				List<AppliedJobs> appliedJobList = userexists.getAppliedJobsList();
				
				appliedJobList.addAll(userAppliedJob.getAppliedJobsList());
				userexists.setAppliedJobsList(appliedJobList);
				UserAppliedJob added =Repo.save(userexists);
				if(added != null) {
					return true;
				}
				else {
					return false;
				}
			}
	}
	catch(AppliedJobNotFoundException e) {
		
		 System.out.println(e.getMessage());
		 return false;
	}	
	}
	/**/
	
	
	


@Override
public List<UserAppliedJob> viewAllUserAppliedJobs() {
	List<UserAppliedJob> userAppliedList = Repo.findAll();
	return userAppliedList;
}

@Override
public UserAppliedJob findUserAppliedJobs(String userId) throws AppliedJobNotFoundException{
	UserAppliedJob result = null;
	try {
		Optional<UserAppliedJob> userFound = Repo.findById(userId);
		if(userFound.isPresent())
			return userFound.get();
	}
	catch(NoSuchElementException e) {
		throw new AppliedJobNotFoundException("User does not exists");
		
	}
	
	return null;
}

@Override
public boolean removeAppliedJob(String userId, int appliedJobId) throws AppliedJobNotFoundException {
	try {
		Optional<UserAppliedJob> userFound = Repo.findById(userId);
		if(userFound.isPresent()) {
			UserAppliedJob target = userFound.get();
			List<AppliedJobs> appliedjoblist = target.getAppliedJobsList();
			Iterator<AppliedJobs> iterator = appliedjoblist.iterator();
			while (iterator.hasNext()){
				AppliedJobs targetJob = iterator.next();
				if(targetJob.getAppliedJobsId() == appliedJobId)
					iterator.remove();
			}
			target.setAppliedJobsList(appliedjoblist);
			Repo.save(target);
			return true;
		}
	}
	catch(NoSuchElementException e) {
		throw new AppliedJobNotFoundException("Applied Job does not exists");
		
	}
	
	return false;
}



}
