package com.example.newssource.controller;

import com.example.newssource.converter.NewsArticleConverter;
import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.model.NewsArticleEntity;
import com.example.newssource.service.NewsArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news/article")
public class NewsArticleController {

    private final NewsArticleServiceImpl articleService;
    @Autowired
    private NewsArticleConverter articleConverter;

    public NewsArticleController(NewsArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/saveArticles")
    public ResponseEntity<String> saveArticles(@RequestBody List<NewsArticleDto> dtos) {
        List<NewsArticleEntity> entities = articleConverter.convertEntities(dtos);
        articleService.saveAll(entities);
        return ResponseEntity.ok("Saved successfully");

    }
}
