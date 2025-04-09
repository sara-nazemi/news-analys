package com.example.newsanalys.controllers;

import com.example.newsanalys.adapter.GenericApiCaller;
import com.example.newsanalys.models.NewsApiResponse;
import com.example.newsanalys.models.NewsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final GenericApiCaller apiCaller;

    @Value("${newsapi.key}")
    private String newsApiKey;

    public NewsController(GenericApiCaller apiCaller) {
        this.apiCaller = apiCaller;
    }

    @GetMapping
    public List<NewsDto> getAllNews(
            @RequestParam(defaultValue = "us") String country,
            @RequestParam(defaultValue = "technology") String keyword
    ) {
        String url = "https://newsapi.org/v2/top-headlines?country=" + country + "&apiKey=" + newsApiKey;

        return apiCaller.fetchAndMap(
                url,
                NewsApiResponse.class,
                response -> response.articles().stream()
                        .map(article -> new NewsDto(
                                article.title(),
                                article.description(),
                                article.url(),
                                article.source() != null ? article.source().name() : "Unknown",
                                article.publishedAt()
                        ))
                        .toList()
        );
    }
}

