package com.example.newssource.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsScheduler {
    private final RestTemplate restTemplate;
    private final NewsArticleServiceImpl articleService;

    public NewsScheduler(RestTemplate restTemplate, NewsArticleServiceImpl articleService) {
        this.restTemplate = restTemplate;
        this.articleService = articleService;
    }

    public void fetchAndSend(){

    }
}
