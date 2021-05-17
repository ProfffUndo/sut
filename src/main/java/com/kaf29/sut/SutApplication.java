package com.kaf29.sut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories("com.kaf29.sut.repo")
public class SutApplication {

	public static void main(String[] args) {
		SpringApplication.run(SutApplication.class, args);
	}

}
