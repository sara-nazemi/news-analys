package com.example.newscall.services;

import com.example.newscall.models.GNewsArticle;

import java.util.List;

public interface GnewsService {
    List<GNewsArticle> getNewsArticles();
}
