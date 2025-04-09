package com.example.newsanalys.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GenericApiCaller {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    public GenericApiCaller(RestTemplate restTemplate, ObjectMapper objectMapper) {

        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

}
