package com.example.newscall.services;

import com.example.newscall.adapter.GenericApiCaller;
import com.example.newscall.models.GNewsArticle;
import com.example.newscall.models.GNewsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GnewsServiceImplTest {

    @Mock
    private GenericApiCaller apiCaller;

    @InjectMocks
    private GnewsServiceImpl gnewsService;

    @BeforeEach
    void setup() throws Exception {
        Field field = GnewsServiceImpl.class.getDeclaredField("gNewsApiKey");
        field.setAccessible(true);
        field.set(gnewsService, "fake-api-key");
    }

    @Test
    void testGetNewsArticles_shouldReturnArticlesFromApi() {
        // Arrange
        String expectedUrl = "https://gnews.io/api/v4/search?country=us&apikey=fake-api-key&max=100&q=ai";

        GNewsArticle article1 = new GNewsArticle("Title 1", "Content 1", "bfjbdsjfb", "jbdsb",
                "kjsfhb", "jhbh", new GNewsArticle.Source("assd", "add"));
        GNewsArticle article2 = new GNewsArticle("Title 2", "Content 2", "bfjbdsjfb2", "jbdsb2",
                "kjsfhb2", "jhbh2", new GNewsArticle.Source("assd2", "add2"));
        GNewsResponse mockResponse = new GNewsResponse(23, List.of(article1, article2));

        ArgumentCaptor<ParameterizedTypeReference<GNewsResponse>> typeRefCaptor =
                ArgumentCaptor.forClass(ParameterizedTypeReference.class);

        when(apiCaller.getData(eq(expectedUrl), any())).thenReturn(mockResponse);

        // Act
        List<GNewsArticle> articles = gnewsService.getNewsArticles();

        // Assert
        assertNotNull(articles);
        assertEquals(2, articles.size());
        assertEquals("Title 1", articles.get(0).title());
        verify(apiCaller).getData(eq(expectedUrl), typeRefCaptor.capture());
    }
}
