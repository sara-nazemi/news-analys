package com.example.newssource.service.feed;

import com.example.newssource.converter.NewsArticleConverter;
import com.example.newssource.repository.NewsArticleRepository;
import com.example.newssource.service.NewsKafkaProducerService;
import com.example.newssource.util.RestTemplateUtil;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class FetchGnewsServiceImplTest {
    private RestTemplateUtil newsScheduler;
    private NewsArticleConverter articleConverter;
    private NewsArticleRepository repository;
    private NewsKafkaProducerService newsFeed;

    private FetchGnewsServiceImpl service;

    @BeforeEach
    void setUp() {
        // by mockito
        newsScheduler = mock(RestTemplateUtil.class);
        articleConverter = mock(NewsArticleConverter.class);
        repository = mock(NewsArticleRepository.class);
        newsFeed = mock(NewsKafkaProducerService.class);

        service = new FetchGnewsServiceImpl(newsScheduler, articleConverter, repository, newsFeed);

    }
}
