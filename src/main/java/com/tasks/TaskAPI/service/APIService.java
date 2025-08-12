package com.tasks.TaskAPI.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

    @Service
    public class APIService {

        private final RestTemplate restTemplate;

        public APIService(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        public String fetchDataFromExternalApi() {
            String url = "https://api.example.com/data";
            return restTemplate.getForObject(url, String.class);
        }
    }
