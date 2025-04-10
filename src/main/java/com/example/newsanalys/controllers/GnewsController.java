package com.example.newsanalys.controllers;

import com.example.newsanalys.adapter.GenericApiCaller;
import com.example.newsanalys.models.GNewsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public GNewsResponse getAllGNews(
            @RequestParam(defaultValue = "us") String country,
            @RequestParam(defaultValue = "technology") String keyword
    ) {
        String url = "https://gnews.io/api/v4/search?country=" + country + "&apikey=" + gNewsApiKey + "&max=" + 100
                + "&q=ai";

        ParameterizedTypeReference<GNewsResponse> response = new ParameterizedTypeReference<>() {
        };
        return apiCaller.getData(url, response);
//        return apiCaller.fetchAndMap(
//                url,
//                GNewsResponse.class,
//                response -> response.articles().stream()
//                        .map(article -> new NewsDto(
//                                article.title(),
//                                article.description(),
//                                article.url(),
//                                article.source() != null ? article.source() : "Unknown",
//                                article.publishedAt()
//                        ))
//                        .toList()
//        );
    }
}
