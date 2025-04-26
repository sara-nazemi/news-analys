package com.example.newssource.service.feed;

import com.example.newssource.converter.NewsArticleConverter;
import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.model.ApiType;
import com.example.newssource.model.NewsArticleEntity;
import com.example.newssource.repository.NewsArticleRepository;
import com.example.newssource.service.NewsKafkaProducerService;
import com.example.newssource.util.RestTemplateUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FetchApiNewsServiceImpl implements FetchApiNewsService, NewsFeeds {

    private final RestTemplateUtil restTemplateUtil;
    private final NewsArticleConverter articleConverter;
    private final NewsArticleRepository repository;
    private final NewsKafkaProducerService newsFeed;

    public FetchApiNewsServiceImpl(RestTemplateUtil restTemplateUtil,
                                   NewsArticleConverter articleConverter,
                                   NewsArticleRepository repository, NewsKafkaProducerService newsFeed) {
        this.restTemplateUtil = restTemplateUtil;
        this.articleConverter = articleConverter;
        this.repository = repository;
        this.newsFeed = newsFeed;
    }

    public void save(List<NewsArticleEntity> entities) {
        for (NewsArticleEntity entity : entities) {
            entity.setApiType(ApiType.NEWSAPI);
            entity.setHashTitle(String.valueOf(entity.hashCode()));
            repository.save(entity);
        }
    }

    public void fetchApiNews() {
        ParameterizedTypeReference<List<NewsArticleDto>> response = new ParameterizedTypeReference<>() {
        };
        List<NewsArticleDto> data = restTemplateUtil.getData("http://localhost:8081/news-call/news/postallnewsapi", response);
        newsFeed.sendInBatches(data);
        List<NewsArticleEntity> entities = articleConverter.convertEntities(data);
        save(entities);
    }

    @Async
    @Transactional
    @Override
    public void storeNews() {
        fetchApiNews();
    }
}
