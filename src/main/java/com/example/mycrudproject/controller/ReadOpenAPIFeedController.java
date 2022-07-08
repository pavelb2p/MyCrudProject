package com.example.mycrudproject.controller;

import com.example.mycrudproject.service.impl.FeedOpenAPIServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReadOpenAPIFeedController {

    private final FeedOpenAPIServiceImpl populateFeedFromOpenAPI;

    @GetMapping("/read-openapi-feed")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void readAPIFeeds() {
        populateFeedFromOpenAPI.populateFeedFromOpenAPI();
    }
}