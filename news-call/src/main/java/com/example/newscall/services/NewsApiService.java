package com.example.newscall.services;

import com.example.newscall.models.NewsApiArticle;

import java.util.List;

public interface NewsApiService {
    List<NewsApiArticle> getNewsApiArticles();

    String createRangeDate();
}
