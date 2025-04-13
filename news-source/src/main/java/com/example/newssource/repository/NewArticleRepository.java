package com.example.newssource.repository;

import com.example.newssource.model.NewsArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NewArticleRepository extends JpaRepository<NewsArticleEntity, Long> {

    void saveAll(List<NewsArticleEntity> entities);
}
