package com.dojo.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UpdateaccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpdateaccountApplication.class, args);
	}

}
