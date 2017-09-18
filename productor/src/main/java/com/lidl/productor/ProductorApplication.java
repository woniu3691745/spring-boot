package com.lidl.productor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class ProductorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductorApplication.class, args);
	}
}
