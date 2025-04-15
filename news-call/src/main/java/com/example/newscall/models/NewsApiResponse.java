package com.example.newscall.models;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsApiResponse {
    private String status;
    private int totalResults;
    private List<NewsApiArticle> articles;

}
