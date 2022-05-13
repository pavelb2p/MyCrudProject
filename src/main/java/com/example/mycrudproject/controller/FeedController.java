package com.example.mycrudproject.controller;

import com.example.mycrudproject.dto.FeedDTO;
import com.example.mycrudproject.dto.mapperdto.FeedDtoMapper;
import com.example.mycrudproject.entity.Feed;
import com.example.mycrudproject.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feeds")
public class FeedController {

    private final FeedService feedService;
    private final FeedDtoMapper feedDtoMapper;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Feed saveFeed(@Valid @RequestBody FeedDTO feedDTO) {
        Feed feed = feedDtoMapper.convertToModel(feedDTO);
        return feedService.saveFeed(feed);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Feed> getFeeds() {
        return feedService.getFeeds()
                .orElseGet(Collections::emptyList);
    }

    @PutMapping("/{recipeId}/update")
    @ResponseStatus(HttpStatus.OK)
    public Feed updateFeed(@Valid @RequestBody FeedDTO feedDTO, @PathVariable String recipeId) {
        Feed feed = feedDtoMapper.convertToModel(feedDTO);
        return feedService.updateFeed(feed, recipeId);
    }

    @DeleteMapping("/{recipeId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeed(@PathVariable String recipeId) {
        feedService.deleteFeed(recipeId);
    }
}