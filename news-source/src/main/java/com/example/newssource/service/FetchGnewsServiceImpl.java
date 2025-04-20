package com.example.newssource.service;

import com.example.newssource.dto.NewsArticleDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class FetchGnewsServiceImpl implements FetchGNewsService {

    private NewsScheduler newsScheduler;

    public FetchGnewsServiceImpl(NewsScheduler newsScheduler) {
        this.newsScheduler = newsScheduler;
    }

    @Async
    public CompletableFuture<List<NewsArticleDto>> fetchGNews() {
        try {
            ParameterizedTypeReference<List<NewsArticleDto>> response = new ParameterizedTypeReference<>() {
            };
            List<NewsArticleDto> data = newsScheduler.getData("http://localhost:8081/news-call/gnews/postallgnews", response);
            return CompletableFuture.completedFuture(data);
        } catch (Exception e) {
            // log error
            return CompletableFuture.completedFuture(Collections.emptyList());    // تا برنامه نپره
        }
    }
}
