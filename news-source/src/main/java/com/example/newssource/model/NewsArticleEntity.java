package com.example.newssource.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NEWS_ARTICLE")
public class NewsArticleEntity extends BaseEntity {
    @Id
    @Column(name = "NEWS_ARTICLE_ID")
    private Long id;
    @Column(name = "NEWS_ARTICLE_TITLE")
    private String title;
    @Column(name = "NEWS_ARTICLE_DESCRIPTION")
    private String description;
    @Column(name = "NEWS_ARTICLE_CONTENT")
    private String content;
    @Column(name = "NEWS_ARTICLE_URL")
    private String url;
    @Column(name = "NEWS_ARTICLE_IMAGE")
    private String image;
    @Column(name = "NEWS_ARTICLE_PUBLISHEDAT")
    private String publishedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NEWS_SOURCE_ID", nullable = false)
    private NewsSourceEntity newsSourceID;
}
