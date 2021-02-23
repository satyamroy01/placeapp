package com.placeapp.FavouriteService.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.placeapp.FavouriteService.controller.UserFavJobController;
import com.placeapp.FavouriteService.model.FavJob;
import com.placeapp.FavouriteService.model.UserFavJob;
import com.placeapp.FavouriteService.services.UserFavService;

@SpringBootTest
public class UserFavController {

	 MockMvc mockMvc;
	    private FavJob favouriteObj;
	    private UserFavJob userFav;
	    private List<FavJob> favList;
	    private List<UserFavJob> userfavList;
	    
	    @InjectMocks
	    private UserFavJobController favouriteController;
	   
	    @Mock
	    private UserFavService favouriteService;
	
	    @BeforeEach
		public void setUp() throws Exception{
			 MockitoAnnotations.initMocks(this);
			 mockMvc = MockMvcBuilders.standaloneSetup(favouriteController).build();
			 favouriteObj=new FavJob();
				favouriteObj.setFavJobId(12);
				favouriteObj.setFavJobName("Software Dev");
				favouriteObj.setFavJobDesc("Do Develop full stack app development");
				favouriteObj.setFavJobLocation("Mumbai");
				favouriteObj.setFavJobCompany("Amazon");
				favouriteObj.setFavJobPostLevel("Senior");
				favList=new ArrayList<FavJob>();
				userfavList=new ArrayList<UserFavJob>();
				favList.add(favouriteObj);
				userFav=new UserFavJob();
				userFav.setUserId("A01");
				userFav.setUserName("Ananya");
				userFav.setFavJobList(favList);
				userfavList.add(userFav);
		}
	
//	    @Test
//	     public void addFavSuccess() throws Exception
//	     {
//	         when(favouriteService.addUserFavJob(userFav)).thenReturn( true);
//	         mockMvc.perform(post("/api/saveUserFav").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userFav)))
//	         .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//	     } 
	    
	    @Test
	    public void addUserFavSuccess() throws Exception {
	    	System.out.println("::::::userFav object :::"+userFav);
	        when(favouriteService.addUserFavJob(any())).thenReturn(true);
	        mockMvc.perform(MockMvcRequestBuilders.post("/api/saveUserFav").contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(userFav)))
	                .andExpect(MockMvcResultMatchers.status().isCreated())
	                .andDo(MockMvcResultHandlers.print());

	    }
	    
	    @Test
	    public void addUserFavFailure() throws Exception {
	        when(favouriteService.addUserFavJob(any())).thenReturn(false);
	        mockMvc.perform(MockMvcRequestBuilders.post("/api/saveUserFav").contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(userFav)))
	                .andExpect(MockMvcResultMatchers.status().isConflict())
	                .andDo(MockMvcResultHandlers.print());

	    }
	    
	    
	    private static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
