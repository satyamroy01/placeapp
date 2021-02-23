package com.placeapp.AppliedJobService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//import com.placeapp.AppliedJobService.filter.JwtFilter;

@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AppliedJobServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppliedJobServiceApplication.class, args);
	}
	
	

	/*
	 * @Bean public FilterRegistrationBean<JwtFilter> jwtFilter() {
	 * UrlBasedCorsConfigurationSource urlconfig=new
	 * UrlBasedCorsConfigurationSource(); CorsConfiguration config=new
	 * CorsConfiguration(); config.setAllowCredentials(true);
	 * config.addAllowedOrigin("*"); config.addAllowedMethod("*");
	 * config.addAllowedHeader("*"); urlconfig.registerCorsConfiguration("/**",
	 * config); FilterRegistrationBean filterbean = new FilterRegistrationBean(new
	 * CorsFilter(urlconfig)); filterbean.setFilter(new JwtFilter());
	 * filterbean.addUrlPatterns("/api/*"); return filterbean; }
	 */

//	@Bean
//	public FilterRegistrationBean getFilter()
//	{
//	UrlBasedCorsConfigurationSource urlconfig=new UrlBasedCorsConfigurationSource();
//	CorsConfiguration config=new CorsConfiguration();
//	config.setAllowCredentials(true);
//	config.addAllowedOrigin("*");
//	config.addAllowedMethod("*");
//	config.addAllowedHeader("*");
//	urlconfig.registerCorsConfiguration("/**", config);
//
//	FilterRegistrationBean filterbean=new FilterRegistrationBean(new CorsFilter(urlconfig));
//	filterbean.setFilter(new JwtFilter());
//
//	filterbean.addUrlPatterns("/api/*");
//	return filterbean;
//	}
}
