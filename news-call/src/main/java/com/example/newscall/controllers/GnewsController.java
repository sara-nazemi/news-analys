package com.example.newscall.controllers;

import com.example.newscall.models.GNewsArticle;
import com.example.newscall.services.GnewsServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/gnews")
public class GnewsController {
    private final GnewsServiceImpl gnewsService;

    public GnewsController(GnewsServiceImpl gnewsService) {
        this.gnewsService = gnewsService;
    }


    @PostMapping("/postallgnews")
    public List<GNewsArticle> postAllGNews() {
        return gnewsService.getNewsArticles();
    }

}


