package com.db.theaterinformationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.db.theaterinformationsystem.model")
public class TheaterInformationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheaterInformationSystemApplication.class, args);
	}

}
