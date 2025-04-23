package com.example.newssource.util;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateUtil {

    private final RestTemplate restTemplate;
    public RestTemplateUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T getData(String url, ParameterizedTypeReference<T> response) {
        RequestEntity<Void> request = RequestEntity.post(url).build();
        return restTemplate.exchange(request, response).getBody();
    }
}
