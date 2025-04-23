package com.example.newssource.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NEWS_ARTICLE")
@ToString
public class NewsArticleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NEWS_ARTICLE_ID")
    private Long id;
    @Column(name = "API_TYPE")
    @Enumerated(EnumType.STRING)
    private ApiType apiType;
    @Column(name = "NEWS_ARTICLE_TITLE", length = 1000)
    private String title;
    @Column(name = "NEWS_ARTICLE_DESCRIPTION", length = 1000)
    private String description;
    @Column(name = "NEWS_ARTICLE_CONTENT", length = 1000)
    private String content;
    @Column(name = "NEWS_ARTICLE_URL", length = 1000)
    private String url;
    @Column(name = "NEWS_ARTICLE_IMAGE", length = 1000)
    private String image;
    @Column(name = "NEWS_ARTICLE_PUBLISHEDAT", length = 1000)
    private String publishedAt;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "NEWS_SOURCE_ID", nullable = false)
    private NewsSourceEntity newsSourceID;
    @Column(name = "hashTitle", unique = true, nullable = false, length = 1000)
    private String hashTitle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsArticleEntity entity)) return false;
        return Objects.equals(title, entity.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }
}
