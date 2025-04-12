package com.example.newssource.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SOURCE")
public class SourceEntity extends BaseEntity {
    @Id
    @Column(name = "SOURCE_ID")
    private Long id;
    @Column(name = "SOURCE_URL")
    private String url;
    @Column(name = "SOURCE_NAME")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "NEWS_SOURCE", cascade = CascadeType.MERGE)
    private List<NewsSourceEntity> newsSources;
}
