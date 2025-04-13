package com.example.newssource.service;

import com.example.newssource.converter.BaseConverter;
import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.model.NewsArticleEntity;
import com.example.newssource.repository.NewArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsArticleServiceImpl implements NewsArticleService {
    private final NewArticleRepository articleRepository;
    @Autowired
    private BaseConverter<NewsArticleEntity, NewsArticleDto> articleConverter;

    public NewsArticleServiceImpl(NewArticleRepository articleRepository) {
        this.articleRepository = articleRepository;

    }

    public void saveAll(List<NewsArticleEntity> entities) {

        articleRepository.saveAll(entities);
    }
}
