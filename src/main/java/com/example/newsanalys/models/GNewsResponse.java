package com.example.newsanalys.models;

import java.util.List;

public record GNewsResponse(
        List<GNewsArticle> articles
) {
}
