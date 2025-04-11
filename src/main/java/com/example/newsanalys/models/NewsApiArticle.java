package com.example.newsanalys.models;

public record NewsApiArticle(
        Source source,
        String author,
        String title,
        String description,
        String url,
        String urlToImage,
        String publishedAt,
        String content
) {
    public record Source(String id,
                         String name) {
    }
}

