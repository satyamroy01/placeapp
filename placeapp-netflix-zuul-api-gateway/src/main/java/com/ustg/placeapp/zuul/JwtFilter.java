package com.ustg.placeapp.zuul;



import java.io.IOException;
import java.security.SignatureException;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;


public class JwtFilter extends GenericFilter  {

//	@Override 
//	  public void addCorsMappings(CorsRegistry registry)
//	  {
//	  registry.addMapping("/**").allowedOrigins("*"); 
//	  }
//	
	@Override
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	 throws IOException, ServletException {
		
		 HttpServletRequest httprequest = (HttpServletRequest) request;
		 HttpServletResponse httpresponse = (HttpServletResponse) response;
		
//		 httpresponse.setHeader("Access-Control-Allow-Origin", "*");
//		 httpresponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");
//		 httpresponse.setHeader("Access-Control-Allow-Credential", "true");
//		 httpresponse.setHeader("Access-Control-Allow-Headers","Content-Type, Authorization, X-Requested-with,observe");
	//	
//		Access-Control-Allow-Headers,
		 System.out.println("Inside JWT filter " + httprequest.getHeader("Authorization"));
		
		
		
		 if (httprequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name()))
		 {
			 chain.doFilter(httprequest, httpresponse);
		 }
		 else
		 {
			 String authheader = httprequest.getHeader("Authorization");
		 		System.out.println("authheader :" + authheader);
		 		
		 if ((authheader == null) || (!authheader.startsWith("Bearer")))
		 {
			 throw new ServletException("JWT Token is missing in authorization");
		 }
		
		
		 String mytoken = authheader.substring(7);
		 try
		 {
			 JwtParser jparser = Jwts.parser().setSigningKey("myjwtkey");
			 Jwt jwtobj = jparser.parse(mytoken);
			 Claims claim = (Claims) jwtobj.getBody();
			 String emailId = claim.getSubject();
			
			 		System.out.println("email" + emailId);
			 		
			 HttpSession session = httprequest.getSession();
			 session.setAttribute("email", emailId);
			 System.out.println("in try -end");
		 }
		
		 catch (MalformedJwtException excep)
		 {
			 throw new ServletException("Token is modified by unauthorized");
		 }
		 	chain.doFilter(httprequest, httpresponse);
		 }
		
	}
	}