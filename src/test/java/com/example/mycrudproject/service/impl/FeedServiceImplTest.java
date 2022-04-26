package com.example.mycrudproject.service.impl;

import com.example.mycrudproject.entity.Feed;
import com.example.mycrudproject.repository.FeedRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;


class FeedServiceImplTest {

    @Mock
    FeedRepository feedRepository;

    @InjectMocks
    FeedServiceImpl feedService;

    @Test
    void shouldSaveFeedTest() {
        List<Feed> feedList = Arrays.asList(
                new Feed("id11",
                        "Vanilla Latte Protein Smoothie",
                        "https://lh3.googleusercontent.com/S12V7Dy_GGI0EzASanzo0TtcBwApUkRUWVc-" +
                                "FXZiwCJpuJYeiTWOUdU9mMEhLs1fPIG50SZQl4glxz1lTM-VCg",
                        "Trending This Week",
                        "Celebrating Sweets",
                        "celebratingsweets.com",
                        "Celebrating Sweets is a food blog filled with sweet, savory"
                )
        );




    }

    @Test
    void getFeeds() {
    }

    @Test
    void updateFeed() {
    }

    @Test
    void deleteFeed() {
    }
}