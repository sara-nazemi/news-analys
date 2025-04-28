package com.example.newssource.service;

import com.example.newssource.dto.NewsArticleDto;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

class NewsKafkaProducerServiceImplTest {
    private NewsKafkaProducerServiceImpl producerService;
    private KafkaTemplate<String, List<NewsArticleDto>> kafkaTemplate;

    @BeforeEach
    void setUp() {
        Mockito.mock(KafkaTemplate.class);
        producerService = new NewsKafkaProducerServiceImpl(kafkaTemplate);
    }
}
