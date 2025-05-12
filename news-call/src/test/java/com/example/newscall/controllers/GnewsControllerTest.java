package com.example.newscall.controllers;

import com.example.newscall.models.GNewsArticle;
import com.example.newscall.services.GnewsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GnewsController.class)
public class GnewsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private GnewsServiceImpl gnewsService;

    @Test
    void postAllGNews_shouldReturnListOfArticles() throws Exception {
        GNewsArticle article1 = new GNewsArticle("Title 1", "Content 1", "bfjbdsjfb", "jbdsb",
                "kjsfhb", "jhbh", new GNewsArticle.Source("assd", "add"));
        GNewsArticle article2 = new GNewsArticle("Title 2", "Content 2", "bfjbdsjfb2", "jbdsb2",
                "kjsfhb2", "jhbh2", new GNewsArticle.Source("assd2", "add2"));
        List<GNewsArticle> mockArticles = List.of(article1, article2);

        when(gnewsService.getNewsArticles()).thenReturn(mockArticles);

        // Act & Assert
        mockMvc.perform(post("/gnews/postallgnews")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.length()").value(2))
                .andExpect((ResultMatcher) jsonPath("$[0].title").value("Title 1"))
                .andExpect((ResultMatcher) jsonPath("$[1].title").value("Title 2"));
    }
}
