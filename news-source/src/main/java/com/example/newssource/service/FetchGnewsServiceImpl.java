package com.example.newssource.service;

import com.example.newssource.converter.NewsArticleConverter;
import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.model.NewsArticleEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchGnewsServiceImpl implements FetchGNewsService {

    private final NewsSchedulerImpl newsScheduler;
    private final NewsServerServiceImpl newsServerService;
    private final NewsArticleConverter articleConverter;

    public FetchGnewsServiceImpl(NewsSchedulerImpl newsSchedulerImpl, NewsServerServiceImpl newsServerService, NewsArticleConverter articleConverter) {
        this.newsScheduler = newsSchedulerImpl;
        this.newsServerService = newsServerService;
        this.articleConverter = articleConverter;
    }

    @Async
    public void fetchGNews() {
        try {
            ParameterizedTypeReference<List<NewsArticleDto>> response = new ParameterizedTypeReference<>() {
            };
            List<NewsArticleDto> data = newsScheduler.getData("http://localhost:8081/news-call/gnews/postallgnews", response);
            List<NewsArticleEntity> entities = articleConverter.convertEntities(data);
            for (NewsArticleEntity entity : entities) {
                newsServerService.seveAsync(entity);
//            return CompletableFuture.completedFuture(data);
            }
        } catch (Exception e) {
            System.err.println("⚠️ GNews Fetch Error: " + e.getMessage());
//            return CompletableFuture.completedFuture(Collections.emptyList());
        }
    }
}
