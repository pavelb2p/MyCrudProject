package com.example.mycrudproject.controller;

import com.example.mycrudproject.entity.Feed;
import com.example.mycrudproject.service.FeedService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FeedControllerTest {
    private static final Feed feed = new Feed(
            "67ef45ad-bcb2-4ca6-8937-7116dc6b9ad3",
            "Orecchiette with Broccoli Rabe and Sweet Italian Sausage",
            "https://lh3.googleusercontent.com/AHySTwcv16pH8",
            "Popular On Yummly",
            "Chef Daniel Holzman",
            "https://www.themeatballshop.com",
            "Daniel Holzman started cooking at age 15");
    private static final List<Feed> feedsList = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FeedService feedService;

    @Test
    void shouldSaveFeedWhenPostMethodTest() throws Exception {
        when(feedService.saveFeed(feed)).thenReturn(feed);
        mockMvc.perform(post("/feeds/save")
                        .content(objectMapper.writeValueAsString(feed))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), content().json(objectMapper.writeValueAsString(feed))
                );
        verify(feedService, times(1)).saveFeed(any(Feed.class));
    }

    @Test
    void shouldGetListFeedsWhenGetMethodTest() throws Exception {
        feedsList.add(feed);
        when(feedService.getFeeds()).thenReturn(Optional.of(feedsList));
        mockMvc.perform(get("/feeds/list")
                        .content(objectMapper.writeValueAsString(feedsList)))
                .andExpectAll(
                        status().isOk(), content().json(objectMapper.writeValueAsString(feedsList)));
        verify(feedService, times(1)).getFeeds();
    }

    @Test
    void shouldUpdateFeedWhenPutMethodTest() throws Exception {
        when(feedService.updateFeed(feed, feed.getId())).thenReturn(feed);
        mockMvc.perform(put("/feeds/{recipeId}/update", "67ef45ad-bcb2-4ca6-8937-7116dc6b9ad3")
                        .content(objectMapper.writeValueAsString(feed))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk());
        verify(feedService, times(1)).updateFeed(any(Feed.class), anyString());
    }

    @Test
    void shouldDeleteFeedWhenDeleteMethod() throws Exception {
        mockMvc.perform(delete("/feeds/{recipeId}/delete", "67ef45ad-bcb2-4ca6-8937-7116dc6b9ad3")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }
}