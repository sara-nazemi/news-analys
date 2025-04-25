package com.example.newssource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.newssource")
@EnableJpaAuditing
@EnableScheduling
@EnableAsync
@EntityScan(basePackages = "com.example.newssource")
public class NewsSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsSourceApplication.class, args);
    }

}
