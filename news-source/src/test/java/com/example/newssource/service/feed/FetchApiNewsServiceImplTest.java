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
import static org.mockito.Mockito.*;

public class FetchApiNewsServiceImplTest {

    private RestTemplateUtil restTemplateUtil;
    private NewsArticleConverter articleConverter;
    private NewsArticleRepository repository;
    private NewsKafkaProducerService kafkaProducer;

    private FetchApiNewsServiceImpl service;

    @BeforeEach
    void setUp() {
        restTemplateUtil = mock(RestTemplateUtil.class);
        articleConverter = mock(NewsArticleConverter.class);
        repository = mock(NewsArticleRepository.class);
        kafkaProducer = mock(NewsKafkaProducerService.class);

        service = new FetchApiNewsServiceImpl(restTemplateUtil, articleConverter, repository, kafkaProducer);
    }

    @Test
    void testFetchApiNews() {
        NewsArticleDto dto1 = new NewsArticleDto("title1", "content1", "hhjg", "gfdfd", "ghdfst",
                "iuyt", new NewsSourceDto("ddhf"));
        NewsArticleDto dto2 = new NewsArticleDto("title2", "content2", "hhjg2", "gfdfd2", "ghdfst2",
                "iuyt2", new NewsSourceDto("ddhf2"));
        List<NewsArticleDto> dtoList = List.of(dto1, dto2);

        when(restTemplateUtil.getData(anyString(), any(ParameterizedTypeReference.class)))
                .thenReturn(dtoList);

        NewsArticleEntity e1 = new NewsArticleEntity();
        NewsArticleEntity e2 = new NewsArticleEntity();
        List<NewsArticleEntity> entityList = List.of(e1, e2);
        when(articleConverter.convertEntities(dtoList)).thenReturn(entityList);

        service.fetchApiNews();
        verify(repository, times(2)).save(any(NewsArticleEntity.class));
        ArgumentCaptor<NewsArticleEntity> captor = ArgumentCaptor.forClass(NewsArticleEntity.class);
        verify(repository, times(2)).save(captor.capture());
        for (NewsArticleEntity entity : captor.getAllValues()) {
            assertEquals(ApiType.NEWSAPI, entity.getApiType());
            assertNotNull(entity.getHashTitle());
        }
    }
}
