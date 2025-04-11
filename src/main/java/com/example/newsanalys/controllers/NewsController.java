package com.example.newsanalys.controllers;

import com.example.newsanalys.adapter.GenericApiCaller;
import com.example.newsanalys.models.NewsApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final GenericApiCaller apiCaller;

    @Value("${newsapi.key}")
    private String newsApiKey;

    public NewsController(GenericApiCaller apiCaller) {
        this.apiCaller = apiCaller;
    }

    @GetMapping("/api")
    public NewsApiResponse getAllNews(
            @RequestParam(defaultValue = "us") String country,
            @RequestParam(defaultValue = "technology") String keyword
    ) {
        // تاریخ امروز
        LocalDate today = LocalDate.now();

        // یک ماه قبل
        LocalDate oneMonthAgo = today.minusMonths(1);

        // فرمت‌دهی به صورت yyyy-MM-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = oneMonthAgo.format(formatter);

        String url = "https://newsapi.org/v2/top-headlines?country=" + country +
                "&apiKey=" + newsApiKey + "&q=ai" + "&sortBy=publishedAt" + "&from=" + formattedDate;

        ParameterizedTypeReference<NewsApiResponse> response = new ParameterizedTypeReference<>() {
        };
        return apiCaller.getData(url, response);
//                url,
//                NewsApiResponse.class,
//                response -> response.articles().stream()
//                        .map(article -> new NewsDto(
//                                article.title(),
//                                article.description(),
//                                article.url(),
//                                article.source() != null ? article.source().name() : "Unknown",
//                                article.publishedAt()
//                        ))
//                        .toList()
//        );
    }
}

