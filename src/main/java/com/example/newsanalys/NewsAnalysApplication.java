package com.example.newsanalys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
public class NewsAnalysApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsAnalysApplication.class, args);

    }

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }


}
