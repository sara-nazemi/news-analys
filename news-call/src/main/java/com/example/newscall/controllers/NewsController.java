package com.example.newscall.controllers;


import com.example.newscall.adapter.GenericApiCaller;
import com.example.newscall.models.NewsApiResponse;
import com.example.newscall.services.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsServiceImpl newsService;
    private final GenericApiCaller apiCaller;

    public NewsController(GenericApiCaller apiCaller) {
        this.apiCaller = apiCaller;
    }

    @GetMapping("/api")
    public NewsApiResponse getAllNews(
            @RequestParam(defaultValue = "us") String country
    ) {

        String url = newsService.createUrlApi(country);
        ParameterizedTypeReference<NewsApiResponse> response = newsService.preparedForResponseByParametrized();
        return apiCaller.getData(url, response);
    }
}

