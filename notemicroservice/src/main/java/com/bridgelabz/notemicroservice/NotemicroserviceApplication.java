package com.bridgelabz.notemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class NotemicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotemicroserviceApplication.class, args);
	}

}


