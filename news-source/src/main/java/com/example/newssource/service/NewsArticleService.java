package com.example.newssource.service;

import com.example.newssource.model.NewsArticleEntity;

import java.util.List;

public interface NewsArticleService {
    void saveAll(List<NewsArticleEntity> entities);

    void fetchAndSend();
}
