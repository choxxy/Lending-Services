package com.assessment.MobileWalletService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MobileWalletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileWalletServiceApplication.class, args);
	}

}
