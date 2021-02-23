package com.placeapp.AppliedJobService.Service;

import java.util.List;

import com.placeapp.AppliedJobService.Exception.AppliedJobAlreadyExistException;
import com.placeapp.AppliedJobService.Exception.AppliedJobNotFoundException;
import com.placeapp.AppliedJobService.model.UserAppliedJob;


public interface UserAppliedJobService {
	
	boolean addUserAppliedJobs(UserAppliedJob userAppliedJob) ;//throws AppliedJobAlreadyExistException;
	List<UserAppliedJob> viewAllUserAppliedJobs();
	UserAppliedJob findUserAppliedJobs(String userId) throws AppliedJobNotFoundException;
	boolean removeAppliedJob(String userId, int appliedJobsId) throws AppliedJobNotFoundException;


}
