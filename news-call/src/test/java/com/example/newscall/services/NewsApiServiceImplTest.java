package com.example.newscall.services;

import com.example.newscall.adapter.GenericApiCaller;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.lang.reflect.Field;

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
}
