package com.example.mycrudproject.service;

import com.example.mycrudproject.entity.Feed;
import org.springframework.stereotype.Service;

public interface FeedService {
    Feed saveFeed(Feed feed);
}
