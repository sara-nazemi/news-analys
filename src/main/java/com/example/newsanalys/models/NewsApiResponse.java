package com.example.newsanalys.models;

import java.util.List;

public record NewsApiResponse(
        String status,
        int totalResults,
        List<NewsApiArticle> articles
) {
}
