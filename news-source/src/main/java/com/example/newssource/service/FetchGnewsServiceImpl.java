package com.example.newssource.service;

import com.example.newssource.converter.NewsArticleConverter;
import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.model.ApiType;
import com.example.newssource.model.NewsArticleEntity;
import com.example.newssource.repository.NewsArticleRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchGnewsServiceImpl implements FetchGNewsService {

    private final NewsSchedulerImpl newsScheduler;
    private final NewsServerServiceImpl newsServerService;
    private final NewsArticleConverter articleConverter;
    private final NewsArticleRepository repository;

    public FetchGnewsServiceImpl(NewsSchedulerImpl newsSchedulerImpl,
                                 NewsServerServiceImpl newsServerService,
                                 NewsArticleConverter articleConverter,
                                 NewsArticleRepository repository) {
        this.newsScheduler = newsSchedulerImpl;
        this.newsServerService = newsServerService;
        this.articleConverter = articleConverter;
        this.repository = repository;
    }

    public void save(List<NewsArticleEntity> entities) {
        for (NewsArticleEntity entity : entities) {
            entity.setApiType(ApiType.GNEWS);
            repository.save(entity);
        }
    }

    @Async
    public void fetchGNews() {
        try {
            ParameterizedTypeReference<List<NewsArticleDto>> response = new ParameterizedTypeReference<>() {
            };
            List<NewsArticleDto> data = newsScheduler.getData("http://localhost:8081/news-call/gnews/postallgnews", response);
            List<NewsArticleEntity> entities = articleConverter.convertEntities(data);
            save(entities);
        } catch (Exception e) {
            System.err.println("⚠️ GNews Fetch Error: " + e.getMessage());
        }
    }
}
