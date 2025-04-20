package com.example.newssource.service;

import com.example.newssource.dto.NewsArticleDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FetchGNewsService {

    CompletableFuture<List<NewsArticleDto>> fetchGNews();
}
