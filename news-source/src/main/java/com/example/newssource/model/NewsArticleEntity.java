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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NEWS_ARTICLE_ID")
    private Long id;
    @Column(name = "NEWS_ARTICLE_TITLE", length = 1000)
    private String title;
    @Column(name = "NEWS_ARTICLE_DESCRIPTION", length = 1000)
    private String description;
    @Column(name = "NEWS_ARTICLE_CONTENT" , length = 1000)
    private String content;
    @Column(name = "NEWS_ARTICLE_URL", length = 1000)
    private String url;
    @Column(name = "NEWS_ARTICLE_IMAGE" , length = 1000)
    private String image;
    @Column(name = "NEWS_ARTICLE_PUBLISHEDAT", length = 1000)
    private String publishedAt;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "NEWS_SOURCE_ID", nullable = false)
    private NewsSourceEntity newsSourceID;
}
