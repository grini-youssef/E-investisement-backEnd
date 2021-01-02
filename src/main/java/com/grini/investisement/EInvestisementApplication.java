package com.grini.investisement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.grini.investisement.config.SwaggerConfiguration;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class EInvestisementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EInvestisementApplication.class, args);
	}

}
