package com.example.mycrudproject.client;

import com.example.mycrudproject.dto.FeedYummlyWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertNull;

@ExtendWith(MockitoExtension.class)
class FeedRestClientTest {
    private static final String FEED_URL = "www.my-test.com";
    private FeedRestClient sut;

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        sut = new FeedRestClient(restTemplate);
        sut.setFeedUrl(FEED_URL);
    }

    @Test
    void shouldThrowRestClientExceptionWhenRestTemplateFails() {
        when(restTemplate.getForObject(FEED_URL, FeedYummlyWrapper.class)).thenThrow(new RestClientException(""));
        FeedYummlyWrapper responseEntity = sut.getFeedsFromOpenAPI();
        assertNull("Couldn't get Feeds from OpenAPI", responseEntity);
    }

    @Test
    void getFeedFromOpenAPITest() {
        when(restTemplate.getForObject(FEED_URL, FeedYummlyWrapper.class)).thenReturn(new FeedYummlyWrapper());
        sut.getFeedsFromOpenAPI();
        verify(restTemplate, times(1)).getForObject(FEED_URL, FeedYummlyWrapper.class);
        assertThat(restTemplate.getForObject(FEED_URL, FeedYummlyWrapper.class)).isNotNull();
    }
}