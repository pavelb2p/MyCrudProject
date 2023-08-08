package com.example.mycrudproject.client;

import com.example.mycrudproject.dto.FeedYummlyWrapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
@Setter
public class FeedRestClient {
    private final RestTemplate restTemplate;

    @Value("${openapi.url.feed}")
    private String feedUrl;

    public FeedYummlyWrapper getFeedsFromOpenAPI() {
        FeedYummlyWrapper responseEntity = null;
        try {
            responseEntity = restTemplate.getForObject(feedUrl, FeedYummlyWrapper.class);
        } catch (RestClientException e) {
            log.error("Couldn't get Feeds from OpenAPI", e);
        }
        return responseEntity;
    }
}