package com.example.newssource.service;

import com.example.newssource.converter.NewsArticleConverter;
import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.model.NewsArticleEntity;
import com.example.newssource.repository.NewArticleRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class NewsArticleServiceImpl implements NewsArticleService {
    private final NewArticleRepository articleRepository;
    private final RestTemplate restTemplate;
    private final NewsArticleConverter newsArticleConverter;

    public NewsArticleServiceImpl(NewArticleRepository articleRepository, RestTemplate restTemplate, NewsArticleConverter newsArticleConverter) {
        this.articleRepository = articleRepository;
        this.restTemplate = restTemplate;
        this.newsArticleConverter = newsArticleConverter;
    }

    public void saveAll(List<NewsArticleEntity> entities) {

        articleRepository.saveAll(entities);
    }

    public void fetchAndSend() {
        ParameterizedTypeReference<NewsArticleDto> response = new ParameterizedTypeReference<>() {
        };
        List<NewsArticleDto> dtos = Arrays.asList(getData("http://localhost:8081/gnews/postallgnews", response));
        List<NewsArticleEntity> entities = newsArticleConverter.convertEntities(dtos);
        saveAll(entities);
    }

    public <T> T getData(String url, ParameterizedTypeReference<T> response) {
        RequestEntity<Void> request = RequestEntity.post(url).build();
        return restTemplate.exchange(request, response).getBody();
    }
}
