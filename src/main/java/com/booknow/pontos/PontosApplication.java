package com.booknow.pontos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PontosApplication {
	public static void main(String[] args) {
		SpringApplication.run(PontosApplication.class, args);
	}

}
