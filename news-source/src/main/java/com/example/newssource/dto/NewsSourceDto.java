package com.example.newssource.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsSourceDto {
    private Long id;
    private String url;
    private String name;
}
