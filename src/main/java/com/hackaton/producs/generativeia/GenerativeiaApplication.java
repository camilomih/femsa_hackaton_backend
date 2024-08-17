package com.hackaton.producs.generativeia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.hackaton.producs.generativeia.feign.client")
public class GenerativeiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenerativeiaApplication.class, args);
	}

}
