package com.example.newscall.controllers;

import com.example.newscall.adapter.GenericApiCaller;
import com.example.newscall.models.GNewsResponse;
import com.example.newscall.services.GnewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Gnews")
public class GnewsController {
    @Autowired
    GnewsServiceImpl gnewsService;
    private final GenericApiCaller apiCaller;

    public GnewsController(GenericApiCaller apiCaller) {
        this.apiCaller = apiCaller;
    }

    @GetMapping("/gnews")
    public GNewsResponse getAllGNews(
            @RequestParam(defaultValue = "us") String country
    ) {
        String url = gnewsService.createUrlGnews(country);
        ParameterizedTypeReference<GNewsResponse> response = gnewsService.preparedForResponseByParametrized();
        return apiCaller.getData(url, response);
    }
}
