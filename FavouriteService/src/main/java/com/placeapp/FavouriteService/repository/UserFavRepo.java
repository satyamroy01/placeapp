package com.placeapp.FavouriteService.repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.placeapp.FavouriteService.model.UserFavJob;


@Repository
public interface UserFavRepo extends MongoRepository<UserFavJob, String>{

	

	

}
