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

    public Feed saveFeed(Feed feed) { return feedRepository.save(feed);
    }

    public Optional<List<Feed>> getFeeds() {
        return Optional.of(feedRepository.findAll());
    }


    public Feed updateFeed(Feed feed) {
        feedRepository.findById(feed.getId())
                .orElseThrow(() -> new FeedNotFoundException("Feed with " + feed.getId() + "not found(update)"));
        return feedRepository.save(feed);
    }


    public void deleteFeed(String id) {
        Feed feedFound = feedRepository.findById(id)
                .orElseThrow(() -> new FeedNotFoundException("Feed with " + id + "not found(delete)"));
        feedRepository.delete(feedFound);


    }
}
