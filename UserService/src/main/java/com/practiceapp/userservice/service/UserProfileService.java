package com.practiceapp.userservice.service;

import java.util.List;
import com.practiceapp.userservice.exception.UserProfileAlreadyExistsException;
import com.practiceapp.userservice.exception.UserProfileNotFoundException;
import com.practiceapp.userservice.model.UserProfile;




public interface UserProfileService {

	UserProfile registerUser(UserProfile user)  throws UserProfileAlreadyExistsException;
	UserProfile updateUser(String userId,UserProfile user) throws UserProfileNotFoundException;
	boolean deleteUser(String userId) throws UserProfileNotFoundException;
	UserProfile getUserById(String UserId) throws UserProfileNotFoundException;
	List<UserProfile> getAllUsers();
}
