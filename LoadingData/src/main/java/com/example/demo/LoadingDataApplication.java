package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@SpringBootApplication
@EnableRabbit
public class LoadingDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadingDataApplication.class, args);
	}

}