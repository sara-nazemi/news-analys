package com.example.newssource.service.feed;

import com.example.newssource.converter.NewsArticleConverter;
import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.dto.NewsSourceDto;
import com.example.newssource.model.ApiType;
import com.example.newssource.model.NewsArticleEntity;
import com.example.newssource.repository.NewsArticleRepository;
import com.example.newssource.service.NewsKafkaProducerService;
import com.example.newssource.util.RestTemplateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class FetchGnewsServiceImplTest {
    private RestTemplateUtil restTemplateUtil;
    private NewsArticleConverter articleConverter;
    private NewsArticleRepository repository;
    private NewsKafkaProducerService newsFeed;

    private FetchGnewsServiceImpl service;

    @BeforeEach
    void setUp() {
        //create mock fields by mockito
        restTemplateUtil = mock(RestTemplateUtil.class);
        articleConverter = mock(NewsArticleConverter.class);
        repository = mock(NewsArticleRepository.class);
        newsFeed = mock(NewsKafkaProducerService.class);
        //create virtual instance but with mock fields
        service = new FetchGnewsServiceImpl(restTemplateUtil, articleConverter, repository, newsFeed);

    }

    @Test
    void testFetchGnews() {
        NewsArticleDto dto1 = new NewsArticleDto("title1", "content1", "hhjg", "gfdfd", "ghdfst",
                "iuyt", new NewsSourceDto("ddhf"));
        NewsArticleDto dto2 = new NewsArticleDto("title2", "content2", "hhjg2", "gfdfd2", "ghdfst2",
                "iuyt2", new NewsSourceDto("ddhf2"));

        List<NewsArticleDto> dtoList = List.of(dto1, dto2);
        //simulation with restTemplate.getData() and when().thenReturn()
        when(restTemplateUtil.getData(anyString(), any(ParameterizedTypeReference.class)))
                .thenReturn(dtoList);

        NewsArticleEntity e1 = new NewsArticleEntity();
        NewsArticleEntity e2 = new NewsArticleEntity();
        List<NewsArticleEntity> entityList = List.of(e1, e2);

        when(articleConverter.convertEntities(dtoList)).thenReturn(entityList);
        service.fetchGNews();
        verify(repository, times(2)).save(any(NewsArticleEntity.class));
        ArgumentCaptor<NewsArticleEntity> captor = ArgumentCaptor.forClass(NewsArticleEntity.class);
        verify(repository, times(2)).save(captor.capture());
        for (NewsArticleEntity entity : captor.getAllValues()) {
            assertEquals(ApiType.GNEWS, entity.getApiType());
            assertNotNull(entity.getHashTitle());
        }
    }
}
