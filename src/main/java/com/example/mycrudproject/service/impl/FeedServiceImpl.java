package com.example.mycrudproject.service.impl;

import com.example.mycrudproject.entity.Feed;
import com.example.mycrudproject.exception.FeedNotFoundException;
import com.example.mycrudproject.repository.FeedRepository;
import com.example.mycrudproject.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;

    public Feed saveFeed(Feed feed) {
        return feedRepository.save(feed);
    }

    public Optional<List<Feed>> getFeeds() {
        return Optional.of(feedRepository.findAll());
    }

    public Feed updateFeed(Feed feed, String recipeId) {
        feedRepository.findById(recipeId)
                .orElseThrow(() -> new FeedNotFoundException("Can't update! Feed with " + recipeId + "not found"));
        return feedRepository.save(feed);
    }

    public void deleteFeed(String recipeId) {
        Feed feedFound = feedRepository.findById(recipeId)
                .orElseThrow(() -> new FeedNotFoundException("Can't delete! Feed with " + recipeId + "not found"));
        feedRepository.delete(feedFound);
    }
}
