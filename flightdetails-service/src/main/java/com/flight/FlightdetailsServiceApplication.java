package com.flight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients 
@EnableDiscoveryClient
public class FlightdetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightdetailsServiceApplication.class, args);
		System.out.println("-------------------Flight Details service is running--------------------");
	}
	
	

}
