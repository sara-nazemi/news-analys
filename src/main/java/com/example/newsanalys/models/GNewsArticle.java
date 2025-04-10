package com.example.newsanalys.models;

public record GNewsArticle(
        String title,
        String description,
        String url,
        Object source,
        String publishedAt
) {
}
