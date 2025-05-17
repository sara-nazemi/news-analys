package com.example.newscall.controllers;


import com.example.newscall.models.NewsApiArticle;
import com.example.newscall.services.NewsApiServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsApiServiceImpl newsApiService;

    public NewsController(NewsApiServiceImpl newsApiService) {
        this.newsApiService = newsApiService;
    }

    @PostMapping("/postallnewsapi")
    public List<NewsApiArticle> postAllNewsApi() {
        return newsApiService.getNewsApiArticles();
    }
}





