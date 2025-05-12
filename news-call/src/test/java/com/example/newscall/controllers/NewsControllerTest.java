package com.example.newscall.controllers;

import com.example.newscall.models.NewsApiArticle;
import com.example.newscall.services.NewsApiServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(NewsController.class)
public class NewsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private NewsApiServiceImpl service;

    @Test
    void postAllNewsApi_shouldReturnListOfArticles() throws Exception {
        String expectedUrl = "https://newsapi.io/api/v4/search?country=us&apikey=fake-api-key&max=100&q=ai";
        NewsApiArticle article1 = new NewsApiArticle(new NewsApiArticle.Source("assd", "add"), "sds", "Content 1", "bfjbdsjfb", "jbdsb",
                "kjsfhb", "jhbh", "kjbjb");
        NewsApiArticle article2 = new NewsApiArticle(new NewsApiArticle.Source("assd2", "add2"), "Title 2", "Content 2", "bfjbdsjfb2", "jbdsb2",
                "kjsfhb2", "jhbh2", "jbjn");
        List<NewsApiArticle> mockArticles = List.of(article1, article2);

        when(service.getNewsApiArticles()).thenReturn(mockArticles);

        mockMvc.perform((RequestBuilder) post("/news/postallnewsapi")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.length()").value(2))
                .andExpect((ResultMatcher) jsonPath("$[0].title").value("Title 1"))
                .andExpect((ResultMatcher) jsonPath("$[1].title").value("Title 2"));

    }
}
