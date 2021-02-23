package com.placeapp.FavouriteService.controller;

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

import com.placeapp.FavouriteService.model.UserFavJob;
import com.placeapp.FavouriteService.services.UserFavService;

@CrossOrigin
@RestController
public class UserFavJobController {
	
	@Autowired
	UserFavService service;
	
	@PostMapping("/api/saveUserFav")
	public ResponseEntity<?> saveUserFavData(@RequestBody() UserFavJob userFavJob)
	{
		UserFavJob userFav;
		try {
			boolean addeduserFav = service.addUserFavJob(userFavJob);
			if(addeduserFav) {
				return new ResponseEntity<UserFavJob>(userFavJob,HttpStatus.CREATED);
				
			}
			else {
				return new ResponseEntity<String>("Job not added to favourites", HttpStatus.CONFLICT);
			}
			
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			
		}
		 
			
			
	 
	}

	
	@GetMapping("/api/showFavJobs")
	public ResponseEntity<?> showAllFavouriteJobs()
	{
		List<UserFavJob> userFavouriteList=service.viewAllUserFavJobs();
		
		return new ResponseEntity<List>(userFavouriteList,HttpStatus.OK);
	}

	@GetMapping("/api/viewItems/{userId}")
	public ResponseEntity<?> viewallItems(@PathVariable("userId") String usrid)
	{
		try {
			UserFavJob items=  service.findUserFavJobs(usrid);
			if(items != null) {
				return new ResponseEntity<UserFavJob>(items,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("No jobs Found", HttpStatus.NOT_FOUND);
			}
			
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	

	
  @DeleteMapping("/api/removeJobFromFavourites/{userId}/{favJobId}")
  public ResponseEntity<?> removeFavjob (@PathVariable("userId") String usrid,@PathVariable("favJobId") int favjobId){
	  try {
		  boolean result = service.removeFavJob(usrid, favjobId);
		  if(result) {
			  return new ResponseEntity<String>("Job Deleted from Favourites", HttpStatus.OK);
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
