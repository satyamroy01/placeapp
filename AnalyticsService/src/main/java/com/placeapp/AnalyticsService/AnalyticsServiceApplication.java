package com.placeapp.AnalyticsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AnalyticsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsServiceApplication.class, args);
	}

}
