package com.example.mycrudproject.service.impl;

import com.example.mycrudproject.UtilsTest;
import com.example.mycrudproject.client.FeedRestClient;
import com.example.mycrudproject.dto.FeedYummly;
import com.example.mycrudproject.dto.FeedYummlyWrapper;
import com.example.mycrudproject.dto.mapperdto.FeedDtoMapper;
import com.example.mycrudproject.exception.FeedNotFoundException;
import com.example.mycrudproject.repository.FeedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedOpenAPIServiceImplTest {
    FeedOpenAPIServiceImpl sut;

    @Mock
    FeedRestClient feedRestClient;
    @Mock
    FeedRepository feedRepository;
    @Mock
    FeedDtoMapper feedDtoMapper;

    @BeforeEach
    public void setUp() {
        sut = new FeedOpenAPIServiceImpl(feedRestClient, feedRepository, feedDtoMapper);
    }

    @Test
    void shouldPopulateFeedFromOpenApiIfFeedNotNullOrEmptyTest() {
        FeedYummly feedYummy = UtilsTest.getValidFeedYummy();
        FeedYummlyWrapper feedYummyWrapper = new FeedYummlyWrapper();
        feedYummyWrapper.setFeed(Collections.singletonList(feedYummy));
        when(feedRestClient.getFeedsFromOpenAPI()).thenReturn(feedYummyWrapper);
        sut.populateFeedFromOpenAPI();
        verify(feedRepository, times(1))
                .saveAll(Collections.singletonList(feedDtoMapper.convertToFeed(feedYummy)));
    }

    @Test
    void shouldThrowFeedNotFoundExceptionIfFeedIsNullTest() {
        when(feedRestClient.getFeedsFromOpenAPI()).thenReturn(null);
        assertThrows(FeedNotFoundException.class, () -> sut.populateFeedFromOpenAPI());
    }

    @Test
    void shouldThrowFeedNotFoundExceptionIfFeedIsEmptyTest() {
        FeedYummlyWrapper feedYummyWrapperEmpty = new FeedYummlyWrapper();
        feedYummyWrapperEmpty.setFeed(Collections.emptyList());
        when(feedRestClient.getFeedsFromOpenAPI()).thenReturn(feedYummyWrapperEmpty);
        assertThrows(FeedNotFoundException.class, () -> sut.populateFeedFromOpenAPI());
    }
}