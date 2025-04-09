package com.example.newsanalys.models;

public record NewsDto(
        String title,
        String description,
        String url,
        String source,
        String publishedAt
) {
}
