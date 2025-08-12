package com.tasks.TaskAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TaskApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApiApplication.class, args);
	}

	/**
	 * This bean is required by APIService to perform HTTP requests.
	 * It provides a centralized place to configure the RestTemplate.
	 * @return a new RestTemplate instance.
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}