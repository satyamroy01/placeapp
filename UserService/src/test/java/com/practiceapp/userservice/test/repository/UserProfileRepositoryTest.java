package com.practiceapp.userservice.test.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.practiceapp.userservice.model.UserProfile;
import com.practiceapp.userservice.repository.UserProfileRepository;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserProfileRepositoryTest {
	
	@Autowired
	private UserProfileRepository userRepository;
	private UserProfile userProfile;
	//private List<UserProfile>userList=null;
	
	@Before
	public void setUp() throws Exception {
		userProfile=new UserProfile();
		userProfile.setUserId("1");
		userProfile.setFirstName("Satyam");
		userProfile.setLastName("kumar");
		userProfile.setContact("8409815151");
		userProfile.setEmailId("satyamroy@live.com");
		userProfile.setCreatedAt(LocalDateTime.now());
		
		//userList.add(userProfile);
	}

	@AfterEach
	public void tearDown() {
		userRepository.deleteAll();
	}
	
	@Test
	public void AddUserProfileTest(){
		userRepository.save(userProfile);
		Optional<UserProfile> user=userRepository.findById("1");
		if(user.isPresent()) {
			Assert.assertEquals("Satyam", user.get().getFirstName());
			Assert.assertEquals("kumar", user.get().getLastName());
			Assert.assertEquals("satyamroy@live.com",user.get().getEmailId());
		}
		//UserProfile addedUser=userRepository.findById("1").get();
		//assertEquals("1", addedUser.getUserId());//(1,is(addedUser.getUserId()));
	}
	
	@Test
	public void getUserByEmailId() 
	{
					userRepository.save(userProfile);
					Assert.assertEquals("satyamroy@live.com", userProfile.getEmailId());
	 }
}
