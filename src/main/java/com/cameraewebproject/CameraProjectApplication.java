package com.cameraewebproject;

import com.cameraewebproject.io.repository.CameraRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class CameraProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CameraProjectApplication.class, args);
	}

}
