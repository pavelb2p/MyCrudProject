package com.example.mycrudproject.service.impl;

import com.example.mycrudproject.client.FeedRestClient;
import com.example.mycrudproject.dto.FeedYummlyWrapper;
import com.example.mycrudproject.dto.mapperdto.FeedDtoMapper;
import com.example.mycrudproject.exception.FeedNotFoundException;
import com.example.mycrudproject.repository.FeedRepository;
import com.example.mycrudproject.service.FeedOpenAPIService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class FeedOpenAPIServiceImpl implements FeedOpenAPIService {

    private final FeedRestClient feedRestClient;
    private final FeedRepository feedRepository;
    private final FeedDtoMapper feedDtoMapper;

    public void populateFeedFromOpenAPI() {
        Optional.ofNullable(feedRestClient.getFeedsFromOpenAPI())
                .map(FeedYummlyWrapper::getFeed)
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream()
                        .map(feedDtoMapper::convertToFeed)
                        .collect(Collectors.toList()))
                .map(feedRepository::saveAll)
                .orElseThrow(() -> new FeedNotFoundException("Feed is null or empty"));
    }
}
