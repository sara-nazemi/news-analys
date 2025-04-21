package com.example.newssource.service;

import com.example.newssource.model.NewsArticleEntity;
import com.example.newssource.repository.NewsArticleRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsServerServiceImpl implements NewsServerService {

    private final NewsArticleRepository repository;

    public NewsServerServiceImpl(NewsArticleRepository repository) {
        this.repository = repository;
    }

    @Async
    @Transactional
    public void seveAsync(NewsArticleEntity entity) {
        try {
            repository.save(entity);
        } catch (Exception e) {
            System.err.println("❌ Error saving: " + entity.getTitle());
            e.printStackTrace();
        }


    }
}
