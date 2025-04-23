package com.example.newssource.service.feed;

import com.example.newssource.model.NewsArticleEntity;

import java.util.List;

public interface FetchGNewsService {

    void fetchGNews();

    void save(List<NewsArticleEntity> entities);
}
