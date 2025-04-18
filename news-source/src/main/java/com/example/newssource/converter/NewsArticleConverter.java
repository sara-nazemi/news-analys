package com.example.newssource.converter;

import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.model.NewsArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsArticleConverter extends BaseConverter<NewsArticleEntity, NewsArticleDto> {
    @Mapping(source = "newsSourceID", target = "newsSourceID.id")
    NewsArticleEntity convertEntity(NewsArticleDto d);

    @Mapping(source = "newsSourceID.id", target = "newsSourceID")
    NewsArticleDto convertDto(NewsArticleEntity d);

}
