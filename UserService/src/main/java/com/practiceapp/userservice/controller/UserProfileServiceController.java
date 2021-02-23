package com.practiceapp.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practiceapp.userservice.exception.UserProfileAlreadyExistsException;
import com.practiceapp.userservice.exception.UserProfileNotFoundException;
import com.practiceapp.userservice.model.UserProfile;
import com.practiceapp.userservice.service.UserProfileService;

@CrossOrigin
@RestController
@RequestMapping("/api/placeapp")
public class UserProfileServiceController {

	@Autowired
	UserProfileService userprofileservice;
	
	public UserProfileServiceController(UserProfileService userprofileservice) {
		this.userprofileservice = userprofileservice;
	}
	@PostMapping("/adduser")
	public ResponseEntity<?> addUser(@RequestBody UserProfile user){
		try {
			UserProfile addUser=userprofileservice.registerUser(user);
			return new ResponseEntity<UserProfile>(addUser,HttpStatus.CREATED);
		}
		catch(UserProfileAlreadyExistsException exception){
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
			
		}
	}
	@PutMapping("/updateuser/{userId}")
	public ResponseEntity<?> updateUser(@RequestBody UserProfile user,@PathVariable("userId") String userId){
		try {
			userprofileservice.updateUser(userId, user);
			return new ResponseEntity<String>("User Profile Updated", HttpStatus.OK);
		}
		catch(UserProfileNotFoundException exception) {
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId){
		
		try {
			boolean check=userprofileservice.deleteUser(userId);
			if(check) {
				return new ResponseEntity<String>("User Profile Deleted", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("User Profile Not Deleted",HttpStatus.NOT_FOUND);
			}
		} catch (UserProfileNotFoundException exception) {
			
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/userprofile/{userid}")
	public ResponseEntity<?> findUserProfile(@PathVariable ("userid")String userId){
		try {
			UserProfile user=userprofileservice.getUserById(userId);
			System.out.println(user.toString()+"::::::::");
			return new ResponseEntity<UserProfile>(user,HttpStatus.OK);
		} catch (UserProfileNotFoundException exception) {
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
			
		}
		
	}
	
}
	
	
	
	
	
	
	

