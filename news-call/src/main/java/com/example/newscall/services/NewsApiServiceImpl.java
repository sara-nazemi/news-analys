package com.example.newscall.services;

import com.example.newscall.adapter.GenericApiCaller;
import com.example.newscall.models.NewsApiArticle;
import com.example.newscall.models.NewsApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NewsApiServiceImpl implements NewsApiService {
    @Value("${newsapi.key}")
    private String newsApiKey;

    private final GenericApiCaller apiCaller;

    public NewsApiServiceImpl(GenericApiCaller apiCaller) {
        this.apiCaller = apiCaller;
    }

    public List<NewsApiArticle> getNewsApiArticles() {
//        String formattedDate = createRangeDate();
        String url = "https://newsapi.org/v2/top-headlines?country=" + "us" + "&category=business" +
                "&apiKey=" + newsApiKey;
        ParameterizedTypeReference<NewsApiResponse> response = new ParameterizedTypeReference<>() {
        };
        NewsApiResponse newsApiResponse = apiCaller.getData(url, response);
        return newsApiResponse.getArticles();
    }

    public String createRangeDate() {
        LocalDate today = LocalDate.now();

        LocalDate oneMonthAgo = today.minusMonths(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return oneMonthAgo.format(formatter);
    }
}
