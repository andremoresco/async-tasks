package com.andremoresco.asynctask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsyncTasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncTasksApplication.class, args);
	}

}
