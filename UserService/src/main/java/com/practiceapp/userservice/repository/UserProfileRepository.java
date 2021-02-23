package com.practiceapp.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practiceapp.userservice.exception.UserProfileNotFoundException;
import com.practiceapp.userservice.model.UserProfile;
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,String> {
	
	 public UserProfile findByEmailIdAndPassword(String emailId,String password) throws UserProfileNotFoundException;
	 UserProfile findByEmailId(String emailId);
	// UserProfile findByEmailId(String emailId);
	 
	 
	

}
