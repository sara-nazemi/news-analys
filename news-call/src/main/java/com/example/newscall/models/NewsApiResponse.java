package com.example.newscall.models;

import java.util.List;

public record NewsApiResponse(
        String status,
        int totalResults,
        List<NewsApiArticle> articles
) {
}
