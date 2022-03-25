package com.example.mycrudproject.service.impl;

import com.example.mycrudproject.entity.Feed;
import com.example.mycrudproject.repository.FeedRepository;
import com.example.mycrudproject.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedRepository feedRepository;


    @Override
    public Feed saveFeed(Feed feed) {
        return feedRepository.save(feed);
    }
}
