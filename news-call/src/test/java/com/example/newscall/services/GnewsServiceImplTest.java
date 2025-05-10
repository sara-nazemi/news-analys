package com.example.newscall.services;

import com.example.newscall.adapter.GenericApiCaller;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GnewsServiceImplTest {

    @Mock
    private GenericApiCaller apiCaller;

    @InjectMocks
    private GnewsServiceImpl gnewsService;
}
