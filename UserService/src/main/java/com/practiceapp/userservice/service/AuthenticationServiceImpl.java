package com.practiceapp.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practiceapp.userservice.exception.UserProfileNotFoundException;
import com.practiceapp.userservice.model.UserProfile;
import com.practiceapp.userservice.repository.UserProfileRepository;
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
UserProfileRepository userProfileRepository;
	@Override
	public UserProfile authenticateUser(UserProfile user) throws UserProfileNotFoundException {
		//try {
		UserProfile target=userProfileRepository.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
			if(target==null)
			{
				System.out.println("user not found:::");
				throw new UserProfileNotFoundException("User does not exists");
			}
			else {
				System.out.println(user.toString()+":::::::::");
				return target;
			}
		}
		/*catch(NullPointerException ex) {
			System.out.println("Null pointer Exception"+ex.getMessage());
			return null;*/
		//}
		
		
	}

