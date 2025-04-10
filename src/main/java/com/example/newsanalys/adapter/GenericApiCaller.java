package com.example.newsanalys.adapter;

import com.example.newsanalys.exception.NewsException;
import com.example.newsanalys.models.NewsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Function;

@Component
public class GenericApiCaller {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    public GenericApiCaller(RestTemplate restTemplate, ObjectMapper objectMapper) {

        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> T getData(String url, ParameterizedTypeReference<T> response) {
        RequestEntity<Void> request = RequestEntity.get(url).build();
        return restTemplate.exchange(request, response).getBody();
    }

    public <T> List<NewsDto> fetchAndMap(String url,
                                         Class<T> responseType,
                                         Function<T, List<NewsDto>> mapper) {

        try {
            String json = restTemplate.getForObject(url, String.class);
            T response = objectMapper.readValue(json, responseType);
            return mapper.apply(response);
        } catch (Exception e) {
            throw new NewsException("Error calling or mapping API: " + url, e);
        }
    }

}
