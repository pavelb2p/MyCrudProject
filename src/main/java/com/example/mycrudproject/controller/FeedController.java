package com.example.mycrudproject.controller;

import com.example.mycrudproject.entity.Feed;
import com.example.mycrudproject.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feeds")
public class FeedController {

    final FeedService feedService;

    @PostMapping("/save")
    public Feed saveFeed(@RequestBody Feed feed) {
        return feedService.saveFeed(feed);
    }

    @GetMapping("/list")
    public Optional<List<Feed>> getFeeds() {
        return feedService.getFeeds();
    }

    @PutMapping("/{recipeId}/update")
    public Feed updateFeed(@RequestBody Feed feed, @PathVariable String recipeId) {
        return feedService.updateFeed(feed);
    }

    @DeleteMapping("/{recipeId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeed(@PathVariable String recipeId) {
        feedService.deleteFeed(recipeId);
    }

}
