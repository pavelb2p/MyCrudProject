package com.example.mycrudproject;

import com.example.mycrudproject.dto.FeedYummly;
import lombok.experimental.UtilityClass;

import java.util.Collections;

@UtilityClass
public class UtilsTest {
    public FeedYummly getValidFeedYummy() {
        return FeedYummly.builder()
                .display(FeedYummly.Display.builder()
                        .displayName("Vanilla Frosting")
                        .profiles(Collections.singletonList(FeedYummly.Display.Profiles.builder()
                                .description("This recipe has no description")
                                .build()))
                        .images(Collections.singletonList("https://lh3.googleusercontent.com/ZhqeoG_nD8ylXOurp2GpvZ7MFlvBjrHnRHeX"))
                        .flag("It's Never Too Early For Dessert")
                        .source(FeedYummly.Display.Source.builder()
                                .sourceDisplayName("Sunset")
                                .sourceSiteUrl("sunset.com")
                                .build())
                        .build())
                .content(FeedYummly.Content.builder()
                        .details(FeedYummly.Content.Details.builder()
                                .recipeId("67ef45ad-bcb2-4ca6-8937-7116dc6b9ad3")
                                .build())
                        .build()
                )
                .build();
    }
}
