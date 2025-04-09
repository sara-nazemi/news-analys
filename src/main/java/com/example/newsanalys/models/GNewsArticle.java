package com.example.newsanalys.models;

public record GNewsArticle(
        String title,
        String description,
        String url,
        String source,
        String publishedAt
) {
}
