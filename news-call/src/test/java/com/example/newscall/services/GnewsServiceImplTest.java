package com.example.newscall.services;

import com.example.newscall.adapter.GenericApiCaller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

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
}
