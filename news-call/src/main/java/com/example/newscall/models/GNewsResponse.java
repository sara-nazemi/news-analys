package com.example.newscall.models;

import java.util.List;

public record GNewsResponse(
        int totalArticles,
        List<GNewsArticle> articles
) {
}
