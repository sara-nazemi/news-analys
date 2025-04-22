package com.example.newssource.service;

import com.example.newssource.converter.NewsArticleConverter;
import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.model.ApiType;
import com.example.newssource.model.NewsArticleEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchApiNewsServiceImpl implements FetchApiNewsService {

    private final NewsSchedulerImpl newsSchedulerImpl;
    private final NewsArticleConverter articleConverter;
    private final NewsServerServiceImpl newsServerService;

    public FetchApiNewsServiceImpl(NewsSchedulerImpl newsSchedulerImpl, NewsArticleConverter articleConverter, NewsServerServiceImpl newsServerService) {
        this.newsSchedulerImpl = newsSchedulerImpl;
        this.articleConverter = articleConverter;
        this.newsServerService = newsServerService;
    }

    @Async
    public void fetchApiNews() {
        try {
            ParameterizedTypeReference<List<NewsArticleDto>> response = new ParameterizedTypeReference<>() {
            };
            List<NewsArticleDto> data = newsSchedulerImpl.getData("http://localhost:8081/news-call/news/postallnewsapi", response);
            List<NewsArticleEntity> entities = articleConverter.convertEntities(data);
            for (NewsArticleEntity entity : entities) {
                entity.setApiType(ApiType.NEWSAPI);
                newsServerService.seveAsync(entity);
//            return CompletableFuture.completedFuture(data);
            }
        } catch (Exception e) {
            System.err.println("⚠️ ApiNews Fetch Error: " + e.getMessage());
//            return CompletableFuture.completedFuture(Collections.emptyList());
        }
    }
}
