package com.example.newscall.controllers;

import com.example.newscall.services.GnewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GnewsController.class)
public class GnewsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private GnewsServiceImpl gnewsService;
}
