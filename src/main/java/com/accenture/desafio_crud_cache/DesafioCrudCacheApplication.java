package com.accenture.desafio_crud_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DesafioCrudCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioCrudCacheApplication.class, args);
	}

}
