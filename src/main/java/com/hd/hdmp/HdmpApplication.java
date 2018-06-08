package com.hd.hdmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HdmpApplication {

	public static void main(String[] args) {
		SpringApplication.run(HdmpApplication.class, args);
	}
}
