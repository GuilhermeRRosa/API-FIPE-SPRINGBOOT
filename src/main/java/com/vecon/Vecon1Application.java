package com.vecon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Vecon1Application{

	public static void main(String[] args) {
		SpringApplication.run(Vecon1Application.class, args);
		
	}

}
