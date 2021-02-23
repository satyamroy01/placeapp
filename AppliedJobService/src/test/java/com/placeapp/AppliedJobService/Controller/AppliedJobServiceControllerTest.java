package com.placeapp.AppliedJobService.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
import com.placeapp.AppliedJobService.Service.UserAppliedJobServiceImpl;
import com.placeapp.AppliedJobService.model.AppliedJobs;
import com.placeapp.AppliedJobService.model.UserAppliedJob;



@SpringBootTest
public class AppliedJobServiceControllerTest {

	
	MockMvc mockMvc;
    private AppliedJobs appliedObj;
    private UserAppliedJob userApplied;
    private List<AppliedJobs> appliedList;
    private List<UserAppliedJob> userapplyList;
    
    @InjectMocks
    private  UserAppliedJobController userAppliedController;
   
    @Mock
    private UserAppliedJobServiceImpl userAppliedService;

    @BeforeEach
	public void setUp() throws Exception{
		 MockitoAnnotations.initMocks(this);
		 mockMvc = MockMvcBuilders.standaloneSetup(userAppliedController).build();
		 appliedObj=new AppliedJobs();
		 appliedObj.setAppliedJobsId(12);
		 appliedObj.setAppliedJobsName("Software Dev");
		 appliedObj.setAppliedJobsDesc("Do Develop full stack app development");
		 appliedObj.setAppliedJobsLocation("Mumbai");
		 appliedObj.setAppliedJobsLocation("Amazon");
		 appliedObj.setAppliedJobsPostLevel("Senior");
		 appliedList=new ArrayList<AppliedJobs>();
		 userapplyList=new ArrayList<UserAppliedJob>();
		 appliedList.add(appliedObj);
		 userApplied=new UserAppliedJob();
		 userApplied.setUserId("A01");
		 userApplied.setUserName("Ananya");
		 userApplied.setAppliedJobsList(appliedList);
		 userapplyList.add(userApplied);
	}
    
    @Test
    public void addUserApplySuccess() throws Exception {
    	System.out.println("::::::userFav object :::"+userApplied);
        when(userAppliedService.addUserAppliedJobs(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/saveUserAppliedJob").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userApplied)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void addUserApplyFailure() throws Exception {
        when(userAppliedService.addUserAppliedJobs(any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/saveUserAppliedJob").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userApplied)))
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
