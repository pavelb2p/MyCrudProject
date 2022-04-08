package com.example.mycrudproject.controller;

import com.example.mycrudproject.entity.Feed;
import com.example.mycrudproject.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @PostMapping("/feeds")
    public Feed saveFeed(@RequestBody Feed feed) {
        return feedService.saveFeed(feed);
    }
}
