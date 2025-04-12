package com.example.newssource.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@ToString
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1234567L;

    @CreatedDate
    @Column(updatable = false)
    private Date insertDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    @Version
    private Integer version;
}
