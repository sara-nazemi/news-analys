package com.example.newssource.service.feed;

import com.example.newssource.model.NewsArticleEntity;

import java.util.List;

public interface FetchApiNewsService {

    void fetchApiNews();
    void save(List<NewsArticleEntity> entities);
}
