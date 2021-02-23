package com.placeapp.FavouriteService.Service;

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

import com.placeapp.FavouriteService.exception.FavJobAlreadyExistException;
import com.placeapp.FavouriteService.exception.FavJobNotFoundException;
import com.placeapp.FavouriteService.model.FavJob;
import com.placeapp.FavouriteService.model.UserFavJob;
import com.placeapp.FavouriteService.repository.UserFavRepo;
import com.placeapp.FavouriteService.services.UserFavServiceImpl;

public class UserFavServiceTest {

	@Mock
	private UserFavRepo  favouriteRepository;
	@InjectMocks
	private UserFavServiceImpl favouriteService;
	private FavJob favouriteObj;
	private UserFavJob user;
	private List<FavJob> favList;
	private List<UserFavJob> userList;
	
	
	@BeforeEach
	public void setUp() throws Exception{
		 MockitoAnnotations.initMocks(this);
		 favouriteObj=new FavJob();
			favouriteObj.setFavJobId(12);
			favouriteObj.setFavJobName("Software Dev");
			favouriteObj.setFavJobDesc("Do Develop full stack app development");
			favouriteObj.setFavJobLocation("Mumbai");
			favouriteObj.setFavJobCompany("Amazon");
			favouriteObj.setFavJobPostLevel("Senior");
			favList=new ArrayList<FavJob>();
			userList=new ArrayList<UserFavJob>();
			favList.add(favouriteObj);
			user=new UserFavJob();
			user.setUserId("A01");
			user.setUserName("Ananya");
			user.setFavJobList(favList);
			userList.add(user);
	}
	
	@Test
	public void favouriteAddFavSuccess() throws FavJobAlreadyExistException  {

		Mockito.when(favouriteRepository.save(user)).thenReturn(user);
		assertEquals(true, favouriteService.addUserFavJob((user)));
	}
//	@Test
//    public void favouriteAddNewsFail() throws FavJobAlreadyExistException, FavJobNotFoundException  {
//       
//       // Mockito.when(favouriteService.findUserFavJobs(user.getUserId())).thenReturn((user));
//		Mockito.when(favouriteRepository.findById("A01")).thenReturn(Optional.of(user));
//        Mockito.when(favouriteRepository.save(user)).thenReturn(user);
//        assertThrows(FavJobAlreadyExistException.class, () -> {
//        	favouriteService.addUserFavJob((user));
//        });
//    }
	
	@Test
	public void viewFavouriteSuccess() throws FavJobNotFoundException  {
	
	Mockito.when(favouriteRepository.findById("A01")).thenReturn(Optional.of(user));
	assertEquals(user, favouriteService.findUserFavJobs("A01"));
	}
	
//	@Test
//	public void viewFavouriteFailure() throws FavJobNotFoundException  {
//	
//	Mockito.when(favouriteRepository.findById("A02")).thenReturn(Optional.of(user));
//	assertThrows(FavJobNotFoundException.class, () -> {
//		favouriteService.findUserFavJobs("A01");
//	});
//	}
	
	
	@Test
	public void favouriteRemoveSuccess() throws FavJobNotFoundException {
		
		Mockito.when(favouriteRepository.findById("A01")).thenReturn(Optional.of(user));
		assertEquals(true, favouriteService.removeFavJob("A01", 12));
	}
	
	@Test
	public void favouriteRemoveFail() throws FavJobNotFoundException {
		
		Mockito.when(favouriteRepository.findById("A01")).thenReturn(Optional.of(user));
		assertThrows(FavJobNotFoundException.class, () -> {
			favouriteService.removeFavJob("A02", 12);
		});
	}
	
	@Test
	public void getAllFavSuccess() {
		Mockito.when(favouriteRepository.findAll()).thenReturn(userList);
		assertEquals(userList, favouriteService.viewAllUserFavJobs());
	}
	}

