package com.example.newscall.services;

import com.example.newscall.adapter.GenericApiCaller;
import com.example.newscall.models.GNewsResponse;
import com.example.newscall.models.NewsApiArticle;
import com.example.newscall.models.NewsApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NewsApiServiceImplTest {
    @Mock
    private GenericApiCaller apiCaller;
    @InjectMocks
    private NewsApiServiceImpl service;

    @BeforeEach
    void setUp() throws Exception {
        Field field = NewsApiServiceImpl.class.getDeclaredField("newsApiKey");
        field.setAccessible(true);
        field.set(service, "fake-api-key");

    }

    @Test
    void testGtNewsApiArticles_shouldReturnArticlesFromApi() {
        String expectedUrl = "https://newsapi.io/api/v4/search?country=us&apikey=fake-api-key&max=100&q=ai";
        NewsApiArticle article1 = new NewsApiArticle(new NewsApiArticle.Source("assd", "add"), "sds", "Content 1", "bfjbdsjfb", "jbdsb",
                "kjsfhb", "jhbh", "kjbjb");
        NewsApiArticle article2 = new NewsApiArticle(new NewsApiArticle.Source("assd2", "add2"), "Title 2", "Content 2", "bfjbdsjfb2", "jbdsb2",
                "kjsfhb2", "jhbh2", "jbjn");
        NewsApiResponse mockResponse = new NewsApiResponse("kjklj", 23, List.of(article1, article2));

        ArgumentCaptor<ParameterizedTypeReference<GNewsResponse>> typeRefCaptor =
                ArgumentCaptor.forClass(ParameterizedTypeReference.class);

        when(apiCaller.getData(eq(expectedUrl), any())).thenReturn(mockResponse);

        List<NewsApiArticle> articles = service.getNewsApiArticles();

        assertNotNull(articles);
        assertEquals(2, articles.size());
        assertEquals("Title 1", articles.get(0).title());
        verify(apiCaller).getData(eq(expectedUrl), typeRefCaptor.capture());
    }
}
