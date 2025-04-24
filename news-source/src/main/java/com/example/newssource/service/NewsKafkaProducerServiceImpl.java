package com.example.newssource.service;

import com.example.newssource.dto.NewsArticleDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsKafkaProducerServiceImpl implements NewsKafkaProducerService {

    private final KafkaTemplate<String, List<NewsArticleDto>> kafkaTemplate;
    @Value("${app.kafka.topic}")
    private String topicName;

    public NewsKafkaProducerServiceImpl(KafkaTemplate<String, List<NewsArticleDto>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendNews(List<NewsArticleDto> newsBatch) {
        kafkaTemplate.send(topicName, newsBatch);
        System.out.println("sent of batch of size : " + newsBatch.size());
    }

    public void sendInBatches(List<NewsArticleDto> allNews) {
        int batchSize = 20;

        for (int i = 1; i < allNews.size(); i += batchSize) {
            List<NewsArticleDto> batch = allNews.subList(i, Math.min(i + batchSize, allNews.size()));
            sendNews(batch);
        }
    }
}
