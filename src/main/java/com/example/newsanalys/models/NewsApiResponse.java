package com.example.newsanalys.models;

import java.util.List;

public record NewsApiResponse(
        List<NewsApiArticle> articles
) {
}
