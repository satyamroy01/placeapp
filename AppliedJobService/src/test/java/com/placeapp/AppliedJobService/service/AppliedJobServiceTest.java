package com.placeapp.AppliedJobService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.placeapp.AppliedJobService.Exception.AppliedJobNotFoundException;
import com.placeapp.AppliedJobService.Repository.UserAppliedJobsRepo;
import com.placeapp.AppliedJobService.Service.UserAppliedJobServiceImpl;
import com.placeapp.AppliedJobService.model.AppliedJobs;
import com.placeapp.AppliedJobService.model.UserAppliedJob;



public class AppliedJobServiceTest {
	
	@Mock
	private UserAppliedJobsRepo  userRepository;
	@InjectMocks
	 UserAppliedJobServiceImpl userAppliedService;
	 private AppliedJobs appliedObj;
	 private UserAppliedJob userApplied;
	 private List<AppliedJobs> appliedList;
	 private List<UserAppliedJob> userapplyList;
	    
	    
	 @BeforeEach
		public void setUp() throws Exception{
			 MockitoAnnotations.initMocks(this);
			 
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
		public void UserAppliedAddFavSuccess()   {
	
			Mockito.when(userRepository.save(userApplied)).thenReturn(userApplied);
			assertEquals(true, userAppliedService.addUserAppliedJobs((userApplied)));
		}
	 
	 @Test
		public void viewAppliedSuccess() throws AppliedJobNotFoundException  {
		
		Mockito.when(userRepository.findById("A01")).thenReturn(Optional.of(userApplied));
		assertEquals(userApplied, userAppliedService.findUserAppliedJobs("A01"));
		}
		
	 @Test
		public void appliedSuccessRemoveSuccess() throws AppliedJobNotFoundException {
			
			Mockito.when(userRepository.findById("A01")).thenReturn(Optional.of(userApplied));
			assertEquals(true, userAppliedService.removeAppliedJob("A01", 12));
		}
	 
//	 @Test
//		public void appliedSuccessRemoveFail() throws AppliedJobNotFoundException {
//			
//			Mockito.when(userRepository.findById("A01")).thenReturn(Optional.of(userApplied));
//			assertThrows(AppliedJobNotFoundException.class, () -> {
//				userAppliedService.removeAppliedJob("A02", 12);
//			});
//		}
	 
	 @Test
		public void getAllAppliedSuccess() {
			Mockito.when(userRepository.findAll()).thenReturn(userapplyList);
			assertEquals(userapplyList, userAppliedService.viewAllUserAppliedJobs());
		}
	 
	 
	 
	 
}
