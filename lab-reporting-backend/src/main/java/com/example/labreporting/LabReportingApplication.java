package com.example.labreporting;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication()
@EnableJpaRepositories(basePackages = "com.example.labreporting.repository")
@EntityScan(basePackages = "com.example.labreporting.model")
public class LabReportingApplication {

	public static void main(String[] args) {

		SpringApplication.run(LabReportingApplication.class, args);
	}
}
