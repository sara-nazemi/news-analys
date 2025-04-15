package com.example.newscall.services;

import com.example.newscall.models.GNewsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class GnewsServiceImpl implements GnewsService {
    @Value("${gnewsapi.key}")
    private String gNewsApiKey;

    public String createUrlGnews() {
        String url = "https://gnews.io/api/v4/search?country=" + "us" + "&apikey=" + gNewsApiKey + "&max=" + 100
                + "&q=ai";
        return url;
    }

    public ParameterizedTypeReference<GNewsResponse> preparedForResponseByParametrized() {
        ParameterizedTypeReference<GNewsResponse> response = new ParameterizedTypeReference<>() {
        };
        return response;
    }
}
