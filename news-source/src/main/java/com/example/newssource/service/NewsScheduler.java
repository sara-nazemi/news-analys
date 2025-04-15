package com.example.newssource.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsScheduler {
    private final RestTemplate restTemplate;

    public NewsScheduler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T getData(String url, ParameterizedTypeReference<T> response) {
        RequestEntity<Void> request = RequestEntity.post(url).build();
        return restTemplate.exchange(request, response).getBody();
    }
}
