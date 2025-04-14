package com.example.newscall.models;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GNewsResponse {
    private int totalArticles;
    private List<GNewsArticle> articles;
}