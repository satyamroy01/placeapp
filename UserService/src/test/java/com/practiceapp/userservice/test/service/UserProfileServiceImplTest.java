package com.practiceapp.userservice.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.practiceapp.userservice.exception.UserProfileAlreadyExistsException;
import com.practiceapp.userservice.exception.UserProfileNotFoundException;
import com.practiceapp.userservice.model.UserProfile;
import com.practiceapp.userservice.repository.UserProfileRepository;
import com.practiceapp.userservice.service.AuthenticationServiceImpl;
import com.practiceapp.userservice.service.UserProfileServiceImpl;

public class UserProfileServiceImplTest {
@Mock
UserProfileRepository userrepo;

UserProfile user;
@InjectMocks
private UserProfileServiceImpl userServiceImpl;
@InjectMocks
private AuthenticationServiceImpl authServiceImpl;

@BeforeEach
public void setUp() throws Exception{
	MockitoAnnotations.initMocks(this);
	user=new UserProfile();
	user.setUserId("101");
	user.setContact("9431850915");
	user.setFirstName("Satyam");
	user.setLastName("kumar");
	user.setCreatedAt(LocalDateTime.now());
	user.setPassword("xyz123");
	user.setEmailId("satyam@gmail.com");
	
}

	@Test
	public void saveUserSuccessTest() throws UserProfileAlreadyExistsException{
		when(userrepo.findById("101")).thenReturn(Optional.empty());
		when(userrepo.save(Mockito.any(UserProfile.class))).thenReturn(new UserProfile());
		Mockito.when(userrepo.save(user)).thenReturn(user);
		UserProfile status = userServiceImpl.registerUser(user);
		assertEquals(user, status);
	}

	@Test
	public void saveUserFailureTest() {

		Mockito.when(userrepo.findByEmailId("satyam@gmail.com")).thenReturn(user);
		Mockito.when(userrepo.save(user)).thenReturn(user);
		assertThrows(UserProfileAlreadyExistsException.class, () -> {
			userServiceImpl.registerUser(user);
		});

	}
	
	@Test
	public void authenticateUserSuccess() throws UserProfileNotFoundException {
		when(userrepo.findByEmailIdAndPassword(user.getEmailId(), user.getPassword())).thenReturn((user));
		assertEquals(user, authServiceImpl.authenticateUser(user));
	}

	@Test
	public void authenticateUserFailure()throws UserProfileNotFoundException {
		when(userrepo.findByEmailIdAndPassword("xyz@gmail.com", "password")).thenThrow(UserProfileNotFoundException.class);
		
	}
	
//	@Test
//    public void getUserByUserIdSuccess() throws UserProfileNotFoundException {
//		//UserProfile user1=userrepo.findByEmailId("satyam@gmail.com");
//		when(userrepo.findByEmailId("satyam@gmail.com")).thenReturn(user);
//       // when(userrepo.findById("101")).thenReturn((Optional)(user));
//        UserProfile fetechedUser = userServiceImpl.getUserById(user.getUserId());
//        assertEquals(user, fetechedUser);
//    }

	
	
	
	
}
