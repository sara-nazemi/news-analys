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
@Table(name = "NEWS_SOURCE")
public class NewsSourceEntity extends BaseEntity {
    @Id
    @Column(name = "NEWS_SOURCE_ID")
    private Long id;
    @Column(name = "NEWS_SOURCE_URL")
    private String url;
    @Column(name = "NEWS_SOURCE_NAME")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "newsSourceID", cascade = CascadeType.MERGE)
    private List<NewsArticleEntity> newsArticles;
}
