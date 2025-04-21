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
    private final NewsServerServiceImpl newsServerService;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(NewsArticleServiceImpl.class);

    public NewsArticleServiceImpl(NewsArticleRepository articleRepository, NewsArticleConverter newsArticleConverter, NewsScheduler newsScheduler,
                                  FetchGnewsServiceImpl gnewsService, FetchApiNewsServiceImpl apiNewsService, NewsServerServiceImpl newsServerService) {
        this.articleRepository = articleRepository;
        this.newsArticleConverter = newsArticleConverter;
        this.newsScheduler = newsScheduler;
        this.gnewsService = gnewsService;
        this.apiNewsService = apiNewsService;
        this.newsServerService = newsServerService;
    }

//    public void saveAll(List<NewsArticleEntity> entities) {
//        List<NewsArticleEntity> uniqueEntity = entities.stream()
//                .filter(entity -> !articleRepository.existByUrl(entity.getUrl()))
//                .toList();
//        if (!uniqueEntity.isEmpty()) {
//        articleRepository.saveAll(entities);

//}

//    public void save(List<NewsArticleEntity> entities) {
//        for (NewsArticleEntity entity : entities) {
//            articleRepository.save(entity);
////            entityManager.flush();
//            logger.info("********************* insert entity ******************" + entity.getId());
//        }
//    }


    @Scheduled(fixedRate = 10000)
    @Transactional
    public void fetchAndSend() {
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
                for (NewsArticleEntity entity : entities) {
                    newsServerService.seveAsync(entity);
                    logger.info("✅ همه داده‌ها با موفقیت ذخیره شدند: {} مورد", entities.size());
                }
            } catch (Exception e) {
                logger.error("❌ خطا در پردازش نهایی داده‌ها", e);
            }
        });
    }
}
