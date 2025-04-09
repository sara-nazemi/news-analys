package com.example.newsanalys.models;

public record NewsApiArticle(
        String title,
        String description,
        String url,
        Source source,
        String publishedAt
) {
    public record Source(String name) {
    }
}

