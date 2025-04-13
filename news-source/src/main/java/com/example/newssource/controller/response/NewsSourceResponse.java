package com.example.newssource.controller.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NewsSourceResponse<T> {
    private String message;
    private String code;
    private Date date;
    private Boolean hasError;
    private T data;
}
