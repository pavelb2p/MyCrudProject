package com.example.mycrudproject.service.impl;

import com.example.mycrudproject.entity.Feed;
import com.example.mycrudproject.exception.FeedNotFoundException;
import com.example.mycrudproject.repository.FeedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedServiceImplTest {
    private static final Feed feed = new Feed(
            "67ef45ad-bcb2-4ca6-8937-7116dc6b9ad3",
            "Orecchiette with Broccoli Rabe and Sweet Italian Sausage",
            "https://lh3.googleusercontent.com/AHySTwcv16pH8",
            "Popular On Yummly",
            "Chef Daniel Holzman",
            "https://www.themeatballshop.com",
            "Daniel Holzman started cooking at age 15");

    private FeedServiceImpl sut;

    @Mock
    private FeedRepository feedRepository;

    @BeforeEach
    void setUp() {
        sut = new FeedServiceImpl(feedRepository);
    }

    @Test
    void shouldSaveFeedSuccessfully() {
        when(feedRepository.save(any())).thenReturn(new Feed());
        sut.saveFeed(any());
        verify(feedRepository, times(1)).save(any());
    }

    @Test
    void shouldGetAllFeedsSuccessfully() {
        List<Feed> feedList = new ArrayList<>();
        feedList.add(feed);
        when(feedRepository.findAll()).thenReturn(feedList);
        sut.getFeeds();
        verify(feedRepository, times(1)).findAll();
        assertThat(sut.getFeeds()).isNotEmpty();
    }

    @Test
    void shouldReturnEmptyListIfFeedIsNotPresent() {
        when(feedRepository.findAll()).thenReturn(Collections.emptyList());
        sut.getFeeds();
        verify(feedRepository, times(1)).findAll();
        assertThat(feedRepository.findAll()).isNotNull();
    }

    @Test
    void shouldUpdateIfFeedIsFound() {
        when(feedRepository.findById(feed.getId())).thenReturn(Optional.of(feed));
        sut.updateFeed(feed, feed.getId());
        verify(feedRepository, times(1)).save(feed);
    }

    @Test()
    void shouldUpdateIfFeedIsNonFound() {
        when(feedRepository.findById(feed.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> sut.updateFeed(feed, feed.getId()))
                .isInstanceOf(FeedNotFoundException.class)
                .hasMessage("Can't update! Feed with ".concat(feed.getId()).concat(" not found"));
    }

    @Test
    void shouldDeleteIfFeedIsFound() {
        when(feedRepository.findById(feed.getId())).thenReturn(Optional.of(feed));
        sut.deleteFeed(feed.getId());
        verify(feedRepository, times(1)).delete(feed);
    }

    @Test
    void shouldThrowsExceptionIfFeedNotFoundWhenDelete() {
        when(feedRepository.findById(feed.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> sut.deleteFeed(feed.getId())).isInstanceOf(FeedNotFoundException.class)
                .hasMessage("Can't delete! Feed with " + feed.getId() + "not found");
    }
}