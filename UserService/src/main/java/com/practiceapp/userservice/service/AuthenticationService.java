package com.practiceapp.userservice.service;

import com.practiceapp.userservice.exception.UserProfileNotFoundException;
import com.practiceapp.userservice.model.UserProfile;

public interface AuthenticationService {
	public UserProfile authenticateUser(UserProfile user) throws UserProfileNotFoundException;

}
