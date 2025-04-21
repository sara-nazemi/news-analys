package com.example.newssource.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsArticleServiceImpl implements NewsArticleService {
    private final FetchGnewsServiceImpl gnewsService;
    private final FetchApiNewsServiceImpl apiNewsService;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(NewsArticleServiceImpl.class);

    public NewsArticleServiceImpl(FetchGnewsServiceImpl gnewsService, FetchApiNewsServiceImpl apiNewsService) {
        this.gnewsService = gnewsService;
        this.apiNewsService = apiNewsService;
    }

    @Scheduled(fixedRate = 10000)
    @Transactional
    public void fetchAndSend() {

        gnewsService.fetchGNews();
        apiNewsService.fetchApiNews();

//        CompletableFuture<List<NewsArticleDto>> futureGNews = gnewsService.fetchGNews();
//        CompletableFuture<List<NewsArticleDto>> futureApiNews = apiNewsService.fetchApiNews();
//        CompletableFuture<Void> allDone = CompletableFuture.allOf(futureGNews, futureApiNews);
//
//        allDone.thenRun(() -> {
//            try {
//                List<NewsArticleDto> gNewsDtos = futureGNews.get();
//                List<NewsArticleDto> apiNewsDtos = futureApiNews.get();
//
//                List<NewsArticleDto> allDtos = new ArrayList<>();
//                allDtos.addAll(gNewsDtos);
//                allDtos.addAll(apiNewsDtos);
//                List<NewsArticleEntity> entities = newsArticleConverter.convertEntities(allDtos);
//                for (NewsArticleEntity entity : entities) {
//                    newsServerService.seveAsync(entity);
//                    logger.info("✅ همه داده‌ها با موفقیت ذخیره شدند: {} مورد", entities.size());
//                }
//            } catch (Exception e) {
//                logger.error("❌ خطا در پردازش نهایی داده‌ها", e);
//            }
//        });
    }
}
