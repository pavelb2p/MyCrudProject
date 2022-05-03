package com.example.mycrudproject.service;

import com.example.mycrudproject.entity.Feed;

import java.util.List;
import java.util.Optional;

public interface FeedService {
    Feed saveFeed(Feed feed);

    Optional<List<Feed>> getFeeds();

    Feed updateFeed(Feed feed, String recipeId);

    void deleteFeed(String stringId);
}
