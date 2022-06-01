package com.sparta.testblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class TestblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestblogApplication.class, args);
	}
}
