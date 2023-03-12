package com.assessment.api.LoanOfferService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LoanOfferServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanOfferServiceApplication.class, args);
	}

}
