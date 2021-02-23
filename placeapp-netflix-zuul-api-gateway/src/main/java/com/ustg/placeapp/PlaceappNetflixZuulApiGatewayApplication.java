package com.ustg.placeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ustg.placeapp.zuul.JwtFilter;


@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class PlaceappNetflixZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaceappNetflixZuulApiGatewayApplication.class, args);
	}
	@Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter()
	{
    	UrlBasedCorsConfigurationSource urlconfig=new UrlBasedCorsConfigurationSource();
    	
// 		CorsConfiguration config=new CorsConfiguration();
// 		config.setAllowCredentials(true);
// 		config.addAllowedOrigin("*");
// 		config.addAllowedMethod("*");
// //		config.addAllowedHeader("*");
// 		
// 		urlconfig.registerCorsConfiguration("/**", config);  new CorsFilter(urlconfig)
 		
 		FilterRegistrationBean filterbean = new FilterRegistrationBean();
 		
 		filterbean.setFilter(new JwtFilter());
 		filterbean.addUrlPatterns("/favouriteService/api/*","/appliedJobService/api/*");

 		//filterbean.addUrlPatterns("/appliedJobService/*");
 		//filterbean.addUrlPatterns("/favouriteService/*");
 		
 		return filterbean;
    }
}