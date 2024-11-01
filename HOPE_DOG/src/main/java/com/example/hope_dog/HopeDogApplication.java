package com.example.hope_dog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class HopeDogApplication {
	public static void main(String[] args) {
		SpringApplication.run(HopeDogApplication.class, args);
	}
}
