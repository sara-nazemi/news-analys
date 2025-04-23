package com.example.newssource.converter;

import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.model.NewsArticleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsArticleConverter extends BaseConverter<NewsArticleEntity, NewsArticleDto> {

    NewsArticleEntity convertEntity(NewsArticleDto d);

    NewsArticleDto convertDto(NewsArticleEntity d);

}
