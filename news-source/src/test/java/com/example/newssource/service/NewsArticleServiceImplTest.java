package com.example.newssource.service;

import com.example.newssource.service.feed.NewsFeeds;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    @Test
    void testFetchAndSend_CallsStoreNewsOnAllFeeds() {
        service.fetchAndSend();

        verify(feed1, times(1)).storeNews();
        verify(feed2, times(1)).storeNews();
    }
}
