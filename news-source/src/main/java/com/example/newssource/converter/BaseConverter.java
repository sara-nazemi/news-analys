package com.example.newssource.converter;

//import org.springframework.web.bind.annotation.Mapping;

import org.mapstruct.Mapping;

import java.util.List;

public interface BaseConverter<E, D> {
    @Mapping(source = "insertDate", target = "insertDate")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    @Mapping(source = "version", target = "version")
    E convertEntity(D d);

    @Mapping(source = "insertDate", target = "insertDate")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    @Mapping(source = "version", target = "version")
    D convertDto(E e);

    List<E> convertEntities(List<D> models);

    List<D> convertDtoes(List<E> models);
}
