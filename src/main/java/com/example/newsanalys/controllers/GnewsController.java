package com.example.newsanalys.controllers;

import com.example.newsanalys.adapter.GenericApiCaller;
import com.example.newsanalys.models.GNewsResponse;
import com.example.newsanalys.models.NewsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Gnews")
public class GnewsController {
    private final GenericApiCaller apiCaller;

    @Value("${gnewsapi.key}")
    private String gNewsApiKey;

    public GnewsController(GenericApiCaller apiCaller) {
        this.apiCaller = apiCaller;
    }

    @GetMapping("/gnews")
    public List<NewsDto> getAllGNews(
            @RequestParam(defaultValue = "us") String country,
            @RequestParam(defaultValue = "technology") String keyword
    ) {
        String url = "https://gnews.io/api/v4/top-headlines?country=" + country + "&apikey=" + gNewsApiKey;

        return apiCaller.fetchAndMap(
                url,
                GNewsResponse.class,
                response -> response.articles().stream()
                        .map(article -> new NewsDto(
                                article.title(),
                                article.description(),
                                article.url(),
                                article.source() != null ? article.source() : "Unknown",
                                article.publishedAt()
                        ))
                        .toList()
        );
    }
}
