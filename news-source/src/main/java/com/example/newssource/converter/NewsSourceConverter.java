package com.example.newssource.converter;

import com.example.newssource.dto.NewsSourceDto;
import com.example.newssource.model.NewsSourceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsSourceConverter extends BaseConverter<NewsSourceEntity, NewsSourceDto> {
    NewsSourceEntity convertEntity(NewsSourceDto d);

    NewsSourceDto convertDto(NewsSourceEntity d);
}
