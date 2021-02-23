package com.placeapp.AppliedJobService.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.placeapp.AppliedJobService.model.UserAppliedJob;

@Repository
public interface UserAppliedJobsRepo extends MongoRepository<UserAppliedJob, String>{

}