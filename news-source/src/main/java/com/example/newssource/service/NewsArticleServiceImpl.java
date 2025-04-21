package com.example.newssource.service;

import com.example.newssource.converter.NewsArticleConverter;
import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.model.NewsArticleEntity;
import com.example.newssource.repository.NewsArticleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NewsArticleServiceImpl implements NewsArticleService {
    private final NewsArticleRepository articleRepository;
    private final NewsArticleConverter newsArticleConverter;
    private final NewsScheduler newsScheduler;
    private final FetchGnewsServiceImpl gnewsService;
    private final FetchApiNewsServiceImpl apiNewsService;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(NewsArticleServiceImpl.class);

    public NewsArticleServiceImpl(NewsArticleRepository articleRepository, NewsArticleConverter newsArticleConverter, NewsScheduler newsScheduler,
                                  FetchGnewsServiceImpl gnewsService, FetchApiNewsServiceImpl apiNewsService) {
        this.articleRepository = articleRepository;
        this.newsArticleConverter = newsArticleConverter;
        this.newsScheduler = newsScheduler;
        this.gnewsService = gnewsService;
        this.apiNewsService = apiNewsService;
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
            articleRepository.save(entity);
//            entityManager.flush();
            logger.info("********************* insert entity ******************" + entity.getId());
        }
    }


    @Scheduled(fixedRate = 10000)
    @Transactional
    public void fetchAndSend() {
//        ParameterizedTypeReference<List<NewsArticleDto>> response = new ParameterizedTypeReference<>() {
//        };
//        List<NewsArticleDto> dtos = newsScheduler.getData("http://localhost:8081/news-call/gnews/postallgnews", response);
//        List<NewsArticleDto> dtosApi = newsScheduler.getData("http://localhost:8081/news-call/news/postallnewsapi", response);
//        List<NewsArticleDto> allDtos = new ArrayList<>();
//        allDtos.addAll(dtos);
//        allDtos.addAll(dtosApi);
//        List<NewsArticleEntity> entities = newsArticleConverter.convertEntities(allDtos);
//        save(entities);
//    }

        CompletableFuture<List<NewsArticleDto>> futureGNews = gnewsService.fetchGNews();
        CompletableFuture<List<NewsArticleDto>> futureApiNews = apiNewsService.fetchApiNews();
        CompletableFuture<Void> allDone = CompletableFuture.allOf(futureGNews, futureApiNews);

        allDone.thenRun(() -> {
            try {
                List<NewsArticleDto> gNewsDtos = futureGNews.get();
                List<NewsArticleDto> apiNewsDtos = futureApiNews.get();

                List<NewsArticleDto> allDtos = new ArrayList<>();
                allDtos.addAll(gNewsDtos);
                allDtos.addAll(apiNewsDtos);
                List<NewsArticleEntity> entities = newsArticleConverter.convertEntities(allDtos);
                save(entities);
                logger.info("✅ همه داده‌ها با موفقیت ذخیره شدند: {} مورد", entities.size());
            } catch (Exception e) {
                logger.error("❌ خطا در پردازش نهایی داده‌ها", e);
            }
        });
    }
}
