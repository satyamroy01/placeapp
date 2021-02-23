package com.practiceapp.userservice.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practiceapp.userservice.exception.UserProfileNotFoundException;
import com.practiceapp.userservice.model.UserProfile;
import com.practiceapp.userservice.service.AuthenticationService;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.Jwts;

@CrossOrigin
@RestController
@RequestMapping("/placeapp/auth")
public class AuthenticationServiceController {

@Autowired
AuthenticationService authService;



	public AuthenticationServiceController(AuthenticationService authService) {
		this.authService = authService;
	}
	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestBody UserProfile user) {
		try {
			UserProfile searchUser = authService.authenticateUser(user);
			if (searchUser != null) {
				String mytoken = generateToken(user);
				HashMap<String, String> mymap = new HashMap<String, String>();
				mymap.put("token", mytoken);
				//return new ResponseEntity<HashMap<String, String>>(mymap, HttpStatus.OK);
				return new ResponseEntity<HashMap>(mymap, HttpStatus.ACCEPTED);
			} 
			//else
			//{
			//	return new ResponseEntity<String>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
			//}
		} 
		catch (UserProfileNotFoundException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
	}
	private String generateToken(UserProfile user) {
		long expirytime = 10_000_000;
		return Jwts.builder().setSubject(user.getEmailId()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expirytime))
				.signWith(SignatureAlgorithm.HS256, "myjwtkey").compact();
	}
}
