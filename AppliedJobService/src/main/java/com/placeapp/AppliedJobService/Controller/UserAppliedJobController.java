package com.placeapp.AppliedJobService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.placeapp.AppliedJobService.Service.UserAppliedJobService;
import com.placeapp.AppliedJobService.model.UserAppliedJob;


@CrossOrigin
@RestController
public class UserAppliedJobController {
	
	@Autowired
	UserAppliedJobService service;
	
	@PostMapping("/api/saveUserAppliedJob")
	public ResponseEntity<?> saveUserAppliedData(@RequestBody() UserAppliedJob userAppliedJob)
	{
		UserAppliedJob userappljob;
		try {
			boolean addeduserAppliedJob = service.addUserAppliedJobs(userAppliedJob);
			if(addeduserAppliedJob) {
				return new ResponseEntity<UserAppliedJob>(userAppliedJob,HttpStatus.CREATED);
				
			}
			else {
				return new ResponseEntity<String>("Job not added to favourites", HttpStatus.CONFLICT);
			}
			
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			
		}
		 	
	 
	}

	
	@GetMapping("/api/showAppliedJobs")
	public ResponseEntity<?> showAllAppliedJobs()
	{
		List<UserAppliedJob> userAppliedJobsList=service.viewAllUserAppliedJobs();
		
		return new ResponseEntity<List>(userAppliedJobsList,HttpStatus.OK);
	}

	@GetMapping("/api/viewItems/{userId}")
	public ResponseEntity<?> viewallItems(@PathVariable("userId") String usrid)
	{
		try {
			UserAppliedJob items=  service.findUserAppliedJobs(usrid);
			if(items != null) {
				return new ResponseEntity<UserAppliedJob>(items,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("No jobs Found", HttpStatus.NOT_FOUND);
			}
			
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	

	
  @DeleteMapping("/api/removeJobFromApplied/{userId}/{appliedJobId}")
  public ResponseEntity<?> removeAppliedjob (@PathVariable("userId") String usrid,@PathVariable("appliedJobId") int appliedJobId){
	  try {
		  boolean result = service.removeAppliedJob(usrid, appliedJobId);
		  if(result) {
			  return new ResponseEntity<String>("Job Deleted from Applied Jobs", HttpStatus.OK);
		  }
		  else {
			  return new ResponseEntity<String>("Job is Not Deleted", HttpStatus.NOT_FOUND);
		  }
		  
	  }
	  catch(Exception e) {
		  return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	  }
  }

}

