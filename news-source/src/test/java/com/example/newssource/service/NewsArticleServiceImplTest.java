package com.example.newssource.service;

import com.example.newssource.service.feed.NewsFeeds;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class NewsArticleServiceImplTest {

    private NewsArticleServiceImpl service;
    private NewsFeeds feed1;
    private NewsFeeds feed2;

    @BeforeEach
    void setUp() {
        feed1 = Mockito.mock(NewsFeeds.class);
        feed2 = Mockito.mock(NewsFeeds.class);
        List<NewsFeeds> feeds = Arrays.asList(feed1, feed2);

        service = new NewsArticleServiceImpl(feeds);
    }
}
