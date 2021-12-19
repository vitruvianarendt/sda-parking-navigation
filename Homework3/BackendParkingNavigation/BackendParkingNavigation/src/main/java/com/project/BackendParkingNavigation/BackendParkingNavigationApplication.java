package com.project.BackendParkingNavigation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.BackendParkingNavigation")
public class BackendParkingNavigationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendParkingNavigationApplication.class, args);
	}
}
