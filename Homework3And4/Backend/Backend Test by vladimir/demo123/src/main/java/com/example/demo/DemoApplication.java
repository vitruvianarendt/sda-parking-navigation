package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//we need to create An API layer Application program interface
@SpringBootApplication
 // @RestController //rest kontroler prai celava klasa da servira endpoints a toa se funkciite :)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
