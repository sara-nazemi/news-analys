package com.example.newssource.service;

import org.springframework.core.ParameterizedTypeReference;

public interface NewsScheduler {
    <T> T getData(String url, ParameterizedTypeReference<T> response);
}
