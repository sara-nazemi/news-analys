package com.example.newssource.converter;

import com.example.newssource.dto.NewsArticleDto;
import com.example.newssource.model.NewsArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsArticleConverter extends BaseConverter<NewsArticleEntity, NewsArticleDto> {
    @Mapping(source = "source.name", target = "newsSourceID.name")
    NewsArticleEntity convertEntity(NewsArticleDto d);

    @Mapping(target = "source.name", source = "newsSourceID.name")
    NewsArticleDto convertDto(NewsArticleEntity d);

}
