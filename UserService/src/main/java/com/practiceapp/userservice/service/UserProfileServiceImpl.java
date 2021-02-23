package com.practiceapp.userservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practiceapp.userservice.exception.UserProfileAlreadyExistsException;
import com.practiceapp.userservice.exception.UserProfileNotFoundException;
import com.practiceapp.userservice.model.UserProfile;
import com.practiceapp.userservice.repository.UserProfileRepository;
@Service
public class UserProfileServiceImpl implements UserProfileService {
	
@Autowired
UserProfileRepository userProfileRepository;

	public UserProfileServiceImpl(UserProfileRepository userProfileRepository) 
	{
	this.userProfileRepository = userProfileRepository;
	}

	@Override
	public UserProfile registerUser(UserProfile user) throws UserProfileAlreadyExistsException {
		UserProfile searchUser=userProfileRepository.findByEmailId(user.getEmailId());
		if(searchUser==null) {
			user.setCreatedAt(LocalDateTime.now());
			return userProfileRepository.save(user);
		}
		else 
		{
			throw new UserProfileAlreadyExistsException("User Profile Already Exists");
			
		}
		/*
		 * user.setCreatedAt(LocalDateTime.now()); UserProfile
		 * adduser=userProfileRepository.save(user); if(adduser!=null) { return adduser;
		 * } else { throw new
		 * UserProfileAlreadyExistsException("User Profile Already Exists"); }
		 */
		
	}

	@Override
	public UserProfile updateUser(String userId, UserProfile user) throws UserProfileNotFoundException {
		

		try{
			UserProfile updateUser=getUserById(userId);
		updateUser.setFirstName(user.getFirstName());
		updateUser.setLastName(user.getLastName());
		updateUser.setEmailId(user.getEmailId());
		updateUser.setPassword(user.getPassword());
		updateUser.setContact(user.getContact());
		updateUser.setCreatedAt(LocalDateTime.now());
		userProfileRepository.save(updateUser);
		return updateUser;
		}
		catch(Exception e) {
			throw new UserProfileNotFoundException("User does not exits");
		}
		
		
	}
	

	@Override
	public boolean deleteUser(String userId) throws UserProfileNotFoundException {
		UserProfile search=getUserById(userId);
		
		if(search != null) {
			userProfileRepository.deleteById(userId);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	/*
	 * public UserProfile getUserById(String userId) throws
	 * UserProfileNotFoundException { UserProfile search =null;
	 * Optional<UserProfile> user=userProfileRepository.findById(userId);
	 * if(user!=null) { search=user.get(); } else { throw new
	 * UserProfileNotFoundException("User Profile Not Found"); } return search; }
	 */
	public UserProfile getUserById(String userId) throws UserProfileNotFoundException {
		UserProfile search = null;
		try {
			Optional<UserProfile> target = userProfileRepository.findById(userId);
			
			if (target.isPresent()) {
				search = target.get();
				//System.out.println("in fin by id:::::"+search.toString());
			}
		} catch (Exception e) {
			throw new UserProfileNotFoundException("User Profile Not Found");
		}
		return search;
	}

	@Override
	public List<UserProfile> getAllUsers() {
		List<UserProfile> allUsers=userProfileRepository.findAll();
		return allUsers;
	}

}
