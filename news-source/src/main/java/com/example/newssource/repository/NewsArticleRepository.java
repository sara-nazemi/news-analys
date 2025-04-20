package com.example.newssource.repository;

import com.example.newssource.model.NewsArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsArticleRepository extends JpaRepository<NewsArticleEntity, Long> {



//    boolean existByUrl(String url);
}
