package com.example.newssource.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsArticleDto extends BaseDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private String url;
    private String image;
    private String publishedAt;
    private NewsSourceDto sourceDto;
    private Long newsSourceID;

}
