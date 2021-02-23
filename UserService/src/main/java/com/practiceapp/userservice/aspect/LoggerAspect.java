//
//  package com.practiceapp.userservice.aspect;
//  
//import java.util.HashMap;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//import com.practiceapp.userservice.model.UserProfile;
//  
//  @Aspect
//  
//  @Component
//  spublic class LoggerAspect {
//  
//	  Logger mylog=LoggerFactory.getLogger(LoggerAspect.class);
//		
//		
//		/* aspect methods for save */
//		@Before("userregisterhandler()")
//		public void beforeUsersave(JoinPoint jp)
//		{
//			mylog.info("Users are saving the details. kind of call is " +  jp.getKind());
//		
//		}
//		
//		@After ("userregisterhandler()")	
//		public void userSave(JoinPoint jp)
//		{
//			mylog.info("Registering the user. Call Type:" +  jp.getKind());
//		}
//		@Around("userregisterhandler()")
//		public Object aroundUserSave(ProceedingJoinPoint proceedobj) throws Throwable
//		{
//		
//			Object obj=proceedobj.proceed();
//			try
//			{
//			
//			ResponseEntity response= (ResponseEntity)obj;
//			UserProfile users=(UserProfile)response.getBody();
//			mylog.info("New user is getting Added with mail id " + users.getEmailId());
//			}
//			catch(Exception e) 
//			{ 
//				mylog.info("Exception raised while adding user " + e.getMessage());
//			}
//			return obj;
//		}
//		
//		/*	************************************************************************************** */
//		
//		
//		/* aspet methods for login */
//		@Before("loginHandler()")
//		public void beforeUserLogin(JoinPoint jp)
//		{
//			
//			mylog.info("Logging The User Call Type:" +  jp.getKind());
//		
//		}
//		@After("loginHandler()")
//		public void afterUserLogin(JoinPoint jp)
//		{
//			mylog.info("User Signing In. Call Type:" +  jp.getKind());
//		}
//		
//		@Around("loginHandler()")
//		public Object aroundLogin(ProceedingJoinPoint proceedobj) throws Throwable
//		{
//			Object obj=proceedobj.proceed();
//			try
//			{
//			ResponseEntity response= (ResponseEntity)obj;
//			HashMap usertoken=(HashMap)response.getBody();
//			mylog.info("User validated before Login token generated : "+!usertoken.isEmpty());
//
//			}
//			catch(Exception e) {
//				mylog.info("Exception Occurred While Login " + e.getMessage());
//			}
//			return obj;
//		}
//
//
//		/*	************************************************************************************** */
//		
//		
//		
//		@Around("userregisterhandler()")
//		public Object aroundGetUserByEmail(ProceedingJoinPoint proceedobj) throws Throwable
//		{
//		
//			Object obj=proceedobj.proceed();
//			try
//			{
//			
//			ResponseEntity response= (ResponseEntity)obj;
//			UserProfile users=(UserProfile)response.getBody();
//			mylog.info("User is Found Based on mail id " + users.getEmailId());
//			}
//			catch(Exception e) 
//			{ 
//				mylog.info("Exception raised while searching user " + e.getMessage());
//			}
//			return obj;
//		}
//		
//		@Pointcut("execution (* com.practiceapp.userservice.controller.UserProfileServiceController.addUser(..))")
//		public void userregisterhandler(){}
//		
//		@Pointcut("execution (* com.practiceapp.userservice.controller.AuthenticationServiceController.validateUser(..))")
//			public void loginHandler() {}
//
//
//
//	}
