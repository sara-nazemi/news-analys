package com.example.newssource.service;

import com.example.newssource.converter.NewsArticleConverter;
import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.model.NewsArticleEntity;
import com.example.newssource.repository.NewsArticleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsArticleServiceImpl implements NewsArticleService {
    private final NewsArticleRepository articleRepository;
    private final NewsArticleConverter newsArticleConverter;
    private final NewsScheduler newsScheduler;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(NewsArticleServiceImpl.class);

    public NewsArticleServiceImpl(NewsArticleRepository articleRepository, NewsArticleConverter newsArticleConverter, NewsScheduler newsScheduler) {
        this.articleRepository = articleRepository;
        this.newsArticleConverter = newsArticleConverter;
        this.newsScheduler = newsScheduler;
    }

    public void saveAll(List<NewsArticleEntity> entities) {
//        List<NewsArticleEntity> uniqueEntity = entities.stream()
//                .filter(entity -> !articleRepository.existByUrl(entity.getUrl()))
//                .toList();
//        if (!uniqueEntity.isEmpty()) {
        articleRepository.saveAll(entities);

    }

    public void save(List<NewsArticleEntity> entities) {
        for (NewsArticleEntity entity : entities) {
            logger.info("********************* insert entity ******************");
            articleRepository.save(entity);
        }
    }


    @Scheduled(fixedRate = 10000)
    @Transactional
    public void fetchAndSend() {
        ParameterizedTypeReference<List<NewsArticleDto>> response = new ParameterizedTypeReference<>() {
        };
        List<NewsArticleDto> dtos = newsScheduler.getData("http://localhost:8081/news-call/gnews/postallgnews", response);
        List<NewsArticleDto> dtosApi = newsScheduler.getData("http://localhost:8081/news-call/news/postallnewsapi", response);
        List<NewsArticleDto> allDtos = new ArrayList<>();
        allDtos.addAll(dtos);
        allDtos.addAll(dtosApi);
        List<NewsArticleEntity> entities = newsArticleConverter.convertEntities(allDtos);
        save(entities);
    }

}
