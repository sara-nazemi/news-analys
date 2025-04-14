package com.example.newssource.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsScheduler {
    private final RestTemplate restTemplate;

    public NewsScheduler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }
}
