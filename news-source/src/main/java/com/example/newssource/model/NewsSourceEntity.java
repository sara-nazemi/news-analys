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
@Table(name = "NEWS_SOURCE")
public class NewsSourceEntity extends BaseEntity {
    @Id
    @Column(name = "NEWS_SOURCE_ID")
    private Long id;
    @Column(name = "NEWS_SOURCE_TITLE")
    private String title;
    @Column(name = "NEWS_SOURCE_DESCRIPTION")
    private String description;
    @Column(name = "NEWS_SOURCE_CONTENT")
    private String content;
    @Column(name = "NEWS_SOURCE_URL")
    private String url;
    @Column(name = "NEWS_SOURCE_IMAGE")
    private String image;
    @Column(name = "NEWS_SOURCE_PUBLISHEDAT")
    private String publishedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOURCE_ID", nullable = false)
    private SourceEntity source;
}
