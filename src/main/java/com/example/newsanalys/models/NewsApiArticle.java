package com.example.newsanalys.models;

public record NewsApiArticle(
        String title,
        String description,
        String content,
        String url,
        String image,
        String publishedAt,
        Source source
) {
    public record Source(String name, String url) {
    }
}

