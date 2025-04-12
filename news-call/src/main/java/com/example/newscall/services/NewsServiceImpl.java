package com.example.newscall.services;

import com.example.newscall.models.NewsApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class NewsServiceImpl implements NewsService {
    @Value("${newsapi.key}")
    private String newsApiKey;

    public String createUrlApi(String country) {
        String formattedDate = createRangeDate();
        String url = "https://newsapi.org/v2/top-headlines?country=" + country +
                "&apiKey=" + newsApiKey + "&q=ai" + "&sortBy=publishedAt" + "&from=" + formattedDate;
        return url;
    }

    public String createRangeDate() {
        LocalDate today = LocalDate.now();

        LocalDate oneMonthAgo = today.minusMonths(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return oneMonthAgo.format(formatter);
    }

    public ParameterizedTypeReference<NewsApiResponse> preparedForResponseByParametrized() {
        ParameterizedTypeReference<NewsApiResponse> response = new ParameterizedTypeReference<>() {
        };
        return response;
    }
}
