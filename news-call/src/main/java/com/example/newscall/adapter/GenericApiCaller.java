package com.example.newscall.adapter;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GenericApiCaller {

    private final RestTemplate restTemplate;

    public GenericApiCaller(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public <T> T getData(String url, ParameterizedTypeReference<T> response) {
        RequestEntity<Void> request = RequestEntity.get(url).build();
        return restTemplate.exchange(request, response).getBody();
    }

}
