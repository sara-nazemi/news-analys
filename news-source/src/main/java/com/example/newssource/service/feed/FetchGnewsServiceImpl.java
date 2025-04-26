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
public class FetchGnewsServiceImpl implements FetchGNewsService, NewsFeeds {

    private final RestTemplateUtil newsScheduler;
    private final NewsArticleConverter articleConverter;
    private final NewsArticleRepository repository;
    private final NewsKafkaProducerService newsFeed;

    public FetchGnewsServiceImpl(RestTemplateUtil restTemplateUtil,
                                 NewsArticleConverter articleConverter,
                                 NewsArticleRepository repository, NewsKafkaProducerService newsFeed) {
        this.newsScheduler = restTemplateUtil;
        this.articleConverter = articleConverter;
        this.repository = repository;
        this.newsFeed = newsFeed;
    }

    public void save(List<NewsArticleEntity> entities) {
        for (NewsArticleEntity entity : entities) {
            entity.setApiType(ApiType.GNEWS);
            entity.setHashTitle(String.valueOf(entity.hashCode()));
            repository.save(entity);
        }
    }

    public void fetchGNews() {
        ParameterizedTypeReference<List<NewsArticleDto>> response = new ParameterizedTypeReference<>() {
        };
        List<NewsArticleDto> data = newsScheduler.getData("http://localhost:8081/news-call/gnews/postallgnews", response);
        newsFeed.sendInBatches(data);
        List<NewsArticleEntity> entities = articleConverter.convertEntities(data);
        save(entities);
    }

    @Async
    @Transactional
    @Override
    public void storeNews() {
        fetchGNews();
    }
}
