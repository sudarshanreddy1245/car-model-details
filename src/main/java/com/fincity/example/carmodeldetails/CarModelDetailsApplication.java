package com.fincity.example.carmodeldetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.fincity.example")
@EnableJpaRepositories(basePackages = { "com.fincity.example.dao" })
@EntityScan(basePackages = { "com.fincity.example.model" })
@EnableCaching 
public class CarModelDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarModelDetailsApplication.class, args);
	}
	

}
