package com.user.service.UserServiceMicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class UserServiceMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceMicroservicesApplication.class, args);
	}

}
