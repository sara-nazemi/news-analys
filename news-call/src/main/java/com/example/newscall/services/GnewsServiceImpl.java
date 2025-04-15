package com.example.newscall.services;

import com.example.newscall.adapter.GenericApiCaller;
import com.example.newscall.models.GNewsArticle;
import com.example.newscall.models.GNewsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GnewsServiceImpl implements GnewsService {
    @Value("${gnewsapi.key}")
    private String gNewsApiKey;

    private final GenericApiCaller apiCaller;

    public GnewsServiceImpl(GenericApiCaller apiCaller) {
        this.apiCaller = apiCaller;
    }

    public List<GNewsArticle> getNewsArticles() {
        String url = "https://gnews.io/api/v4/search?country=" + "us" + "&apikey=" + gNewsApiKey + "&max=" + 100
                + "&q=ai";
        ParameterizedTypeReference<GNewsResponse> response = new ParameterizedTypeReference<>() {
        };
        GNewsResponse gNewsResponse = apiCaller.getData(url, response);
        return gNewsResponse.getArticles();
    }
}
