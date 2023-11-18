package com.eyeassist.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class EyeassistCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EyeassistCoreApplication.class, args);
	}

}
