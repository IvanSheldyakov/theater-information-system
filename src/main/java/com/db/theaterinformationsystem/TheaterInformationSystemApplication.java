package com.db.theaterinformationsystem;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan(basePackages = "com.db.theaterinformationsystem.model")
public class TheaterInformationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheaterInformationSystemApplication.class, args);
	}
	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title("THEATER")
						.description("RESTful API")
						.version("1.0"));
	}


}
