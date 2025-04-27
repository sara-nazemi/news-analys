package com.example.newssource.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsArticleDto extends BaseDto {
    private String title;
    private String description;
    private String content;
    private String url;
    private String image;
    private String publishedAt;
    private NewsSourceDto source;
}
