package com.example.newssource.service.feed;

import com.example.newssource.converter.NewsArticleConverter;
import com.example.newssource.repository.NewsArticleRepository;
import com.example.newssource.service.NewsKafkaProducerService;
import com.example.newssource.util.RestTemplateUtil;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class FetchApiNewsServiceImplTest {

    private RestTemplateUtil restTemplateUtil;
    private NewsArticleConverter articleConverter;
    private NewsArticleRepository repository;
    private NewsKafkaProducerService kafkaProducer;

    private FetchApiNewsServiceImpl service;

    @BeforeEach
    void setUp() {
        restTemplateUtil = mock(RestTemplateUtil.class);
        articleConverter = mock(NewsArticleConverter.class);
        repository = mock(NewsArticleRepository.class);
        kafkaProducer = mock(NewsKafkaProducerService.class);

        service = new FetchApiNewsServiceImpl(restTemplateUtil, articleConverter, repository, kafkaProducer);
    }
}
