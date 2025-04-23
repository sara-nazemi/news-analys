package com.example.newssource.service;

import com.example.newssource.dto.NewsArticleDto;

import java.util.List;

public interface NewsKafkaProducerService {
    void sendNews(List<NewsArticleDto> newsBatch);
}
