package com.example.newssource.service;

import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.dto.NewsSourceDto;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class NewsKafkaProducerServiceImplTest {
    private NewsKafkaProducerServiceImpl producerService;
    private KafkaTemplate<String, List<NewsArticleDto>> kafkaTemplate;

    @BeforeEach
    void setUp() {
        Mockito.mock(KafkaTemplate.class);
        producerService = new NewsKafkaProducerServiceImpl(kafkaTemplate);
    }

    void testSendNews() {
        List<NewsArticleDto> newsBatch = new ArrayList<>();
        newsBatch.add(new NewsArticleDto("title1", "content1", "asdsad", "dasdads", "sdsada", "asdasda", new NewsSourceDto("kkn")));
        producerService.sendNews(newsBatch);
        verify(kafkaTemplate, times(1)).send("test-topic", newsBatch);

    }
}
