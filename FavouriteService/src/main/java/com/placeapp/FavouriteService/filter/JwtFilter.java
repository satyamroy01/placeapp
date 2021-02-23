//package com.placeapp.FavouriteService.filter;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.GenericFilter;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwt;
//import io.jsonwebtoken.JwtParser;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureException;
//
//public class JwtFilter extends GenericFilter implements WebMvcConfigurer{
//
//	/**
//	 * 
//	 */
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//	throws IOException, ServletException {
//	HttpServletRequest httpRequest = (HttpServletRequest) request;
//	HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//	 httpResponse.setHeader("Access-Control-Allow-Origin", httpRequest.getHeader("origin"));
//	httpResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");
//	httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//	httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
//	if (httpRequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
//	chain.doFilter(httpRequest, httpResponse);
//	} else {
//
//	 String authHeader = httpRequest.getHeader("Authorization");
//
//	 if ((authHeader == null) || (!authHeader.startsWith("Bearer"))) {
//	throw new ServletException("JWT Token is missing in authorization");
//	}
//
//	 String myToken = authHeader.substring(7);
//
//	 try {
//	JwtParser jparser = Jwts.parser().setSigningKey("myjwtkey");
//	Jwt jwtObj = jparser.parse(myToken);
//
//	 Claims claims = (Claims) jwtObj.getBody();
//	String emailId = claims.getSubject();
//	HttpSession session = httpRequest.getSession();
//	session.setAttribute("email", emailId);
//	} catch (SignatureException e) {
//	throw new ServletException("Signamture mismatch or token expired");
//	} catch (MalformedJwtException e) {
//	throw new ServletException("Token is modified by unauthorised");
//	}
//	chain.doFilter(httpRequest, httpResponse);
//	}
//	}
//
//	}