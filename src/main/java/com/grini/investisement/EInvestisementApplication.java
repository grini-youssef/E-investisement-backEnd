package com.grini.investisement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EInvestisementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EInvestisementApplication.class, args);
	}

}
