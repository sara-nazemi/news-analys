package com.example.newssource.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
@ToString
public class BaseDto {
    private Date insertDate;

    private Date lastModifiedDate;

    private Integer version;
}
