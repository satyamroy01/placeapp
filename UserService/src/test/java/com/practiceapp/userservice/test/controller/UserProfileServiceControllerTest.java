package com.practiceapp.userservice.test.controller;



import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import static org.mockito.Matchers.any;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practiceapp.userservice.controller.AuthenticationServiceController;
import com.practiceapp.userservice.controller.UserProfileServiceController;
import com.practiceapp.userservice.exception.UserProfileAlreadyExistsException;
import com.practiceapp.userservice.model.UserProfile;
import com.practiceapp.userservice.service.AuthenticationService;
import com.practiceapp.userservice.service.UserProfileService;

@SpringBootTest
public class UserProfileServiceControllerTest {
	
	MockMvc mvc;
	MockMvc authmvc;

    @Mock
    private UserProfileService userService;
    @Mock
    private AuthenticationService authService;

    @InjectMocks
    UserProfileServiceController userController;
    
    @InjectMocks
    AuthenticationServiceController authController;
     UserProfile user;

     @Before
     public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
         mvc = MockMvcBuilders.standaloneSetup(userController).build();
         authmvc=MockMvcBuilders.standaloneSetup(authController).build();
         user = new UserProfile();
         user.setContact("85000005");
         //user.setCreatedAt(LocalDateTime.now());
         user.setEmailId("karan@gmail.com");
         user.setFirstName("karan");
         user.setLastName("singh");
         user.setPassword("password");
         user.setUserId("103");
         
 }
     
    	
	
	
    
    @Test
    public void addUserDataSuccess() throws Exception {
        when(userService.registerUser(user)).thenReturn(user);
        mvc.perform(MockMvcRequestBuilders.post("/api/placeapp/adduser").contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(user))).andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    
    @Test
    public void addUserDataFailure() throws Exception {
        when(userService.registerUser(any())).thenThrow(UserProfileAlreadyExistsException.class);
        mvc.perform(MockMvcRequestBuilders.post("/api/placeapp/adduser").contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(user))).andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }
    
//    @Test
//    public void getUserByID() throws Exception {
//		Mockito.when(userService.registerUser(user)).thenReturn(user);
//		String userId = user.getUserId();
//		when(userService.getUserById(userId)).thenReturn(user);
//        mvc.perform(MockMvcRequestBuilders.get("/api/placeapp/userprofile/103")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isAccepted());
//    }
    
//    @Test
//	public void validateUserSuccess() throws Exception {
//	        Mockito.when(authService.authenticateUser(user)).thenReturn(user);
//	        //Mockito.when(useservice.getUsersById("u10")).thenReturn(user);
//	        authmvc.perform(MockMvcRequestBuilders.post("/placeapp/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//	                .andExpect(MockMvcResultMatchers.status().isForbidden()).andDo(MockMvcResultHandlers.print());
//
//	}
    
//    @Test
//    public void testUserLoginSuccess() throws Exception {
//        UserProfile usernew = new UserProfile();
//        usernew.setUserId("151");
//        usernew.setEmailId("karan@gmail.com");
//        usernew.setPassword("password");
//        // Mockito.when(userService.saveUserDetails(user)).thenReturn(user);
//        Mockito.when(authService.authenticateUser(usernew)).thenReturn(user);
//        authmvc.perform(MockMvcRequestBuilders.post("/placeapp/auth/login").contentType(MediaType.APPLICATION_JSON)
//                .content(jsonToString(usernew))).andExpect(MockMvcResultMatchers.status().isAccepted())
//                .andDo(MockMvcResultHandlers.print());
//    }
    
    
    
	
//	@Test
//	public void validateUserFailure() throws Exception {
//		authmvc.perform(MockMvcRequestBuilders.post("/placeapp/auth/login").contentType(MediaType.APPLICATION_JSON)
//				.content(jsonToString(user))).andExpect(MockMvcResultMatchers.status().isForbidden());
//	}
    
    

    
    
  //convert user method for validate user failure 
	
    private String jsonToString(final Object obj) {
        String result;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "Json processing error";
        }
        return result;
    }

    
    
}
