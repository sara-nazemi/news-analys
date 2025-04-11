package com.example.newscall.models;

public record GNewsArticle(
        String title,
        String description,
        String content,
        String url,
        String image,
        String publishedAt,
        Source source
) {
    public record Source(String name,
                         String url) {
    }

}
