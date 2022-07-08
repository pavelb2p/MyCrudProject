package com.example.mycrudproject.dto.mapperdto;

import com.example.mycrudproject.dto.FeedDTO;
import com.example.mycrudproject.dto.FeedYummly;
import com.example.mycrudproject.dto.mapperinterface.DtoMapper;
import com.example.mycrudproject.entity.Feed;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class FeedDtoMapper implements DtoMapper<FeedDTO, Feed> {
    private final ModelMapper modelMapper = new ModelMapper();

    public Feed convertToModel(FeedDTO feedDTO) {
        return modelMapper.map(feedDTO, Feed.class);
    }

    public Feed convertToFeed(FeedYummly feedYummly) {
        Feed feed = new Feed();
        setFeedFieldsFromFeedYummly(feed, feedYummly);
        return feed;
    }

    public FeedDTO convertToDTO(Feed feed) {
        return modelMapper.map(feed, FeedDTO.class);
    }

    public void setFeedFieldsFromFeedYummly(Feed feed, FeedYummly feedYummly) {
        Optional<FeedYummly.Display> getFeedYummlyDisplay = Optional.ofNullable(feedYummly)
                .map(FeedYummly::getDisplay);
        feed.setId(Optional.ofNullable(feedYummly)
                .map(FeedYummly::getContent)
                .map(FeedYummly.Content::getDetails)
                .map(FeedYummly.Content.Details::getRecipeId)
                .orElse("This recipe has no ID"));
        feed.setDisplayName(getFeedYummlyDisplay
                .map(FeedYummly.Display::getDisplayName)
                .orElse("This recipe has no DisplayName"));
        feed.setFlag(getFeedYummlyDisplay
                .map(FeedYummly.Display::getFlag)
                .orElse("This recipe has no Flag"));
        feed.setSourceDisplayName(getFeedYummlyDisplay
                .map(FeedYummly.Display::getSource)
                .map(FeedYummly.Display.Source::getSourceDisplayName)
                .orElse("This recipe has no SourceDisplayName"));
        feed.setSourceSiteUrl(getFeedYummlyDisplay
                .map(FeedYummly.Display::getSource)
                .map(FeedYummly.Display.Source::getSourceSiteUrl)
                .orElse("This recipe has no SourceDisplayName"));
        feed.setDescription(getFeedYummlyDisplay
                .map(FeedYummly.Display::getProfiles)
                .filter(profiles -> !profiles.isEmpty())
                .map(x -> x.get(0))
                .map(FeedYummly.Display.Profiles::getDescription)
                .orElse("This recipe has no Description"));
        feed.setImages(String.valueOf(getFeedYummlyDisplay
                .map(FeedYummly.Display::getImages)
                .orElse(Collections.singletonList("This recipe has no Images"))));
    }
}