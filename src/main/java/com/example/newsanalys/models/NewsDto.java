package com.example.newsanalys.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsDto {

    String title;
    String description;
    String url;
    String source;
    String publishedAt;
}
