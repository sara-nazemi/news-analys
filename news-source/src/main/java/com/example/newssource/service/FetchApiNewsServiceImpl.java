package com.example.newssource.service;

import com.example.newssource.dto.NewsArticleDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class FetchApiNewsServiceImpl implements FetchApiNewsService {

    private NewsScheduler newsScheduler;

    public FetchApiNewsServiceImpl(NewsScheduler newsScheduler) {
        this.newsScheduler = newsScheduler;
    }

    @Async
    public CompletableFuture<List<NewsArticleDto>> fetchApiNews() {
        try {
            ParameterizedTypeReference<List<NewsArticleDto>> response = new ParameterizedTypeReference<>() {
            };
            List<NewsArticleDto> data = newsScheduler.getData("http://localhost:8081/news-call/news/postallnewsapi", response);
            return CompletableFuture.completedFuture(data);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(Collections.emptyList());
        }
    }
}
