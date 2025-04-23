package com.example.newssource.service;

import com.example.newssource.service.feed.NewsFeeds;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsArticleServiceImpl implements NewsArticleService {

    private final List<NewsFeeds> newsFeeds;

    public NewsArticleServiceImpl(List<NewsFeeds> newsFeeds) {
        this.newsFeeds = newsFeeds;
    }

    @Scheduled(fixedRate = 20000)
    public void fetchAndSend() {
        for (NewsFeeds newsFeed : newsFeeds) {
            newsFeed.storeNews();
        }
    }
}
