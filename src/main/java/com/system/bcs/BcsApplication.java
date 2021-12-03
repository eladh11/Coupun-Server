package com.system.bcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcsApplication.class, args);
		System.out.println("IoC Container was loaded!!!");
	}

}