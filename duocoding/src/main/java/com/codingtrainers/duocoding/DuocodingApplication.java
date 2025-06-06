package com.codingtrainers.duocoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DuocodingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuocodingApplication.class, args);
	}

}
