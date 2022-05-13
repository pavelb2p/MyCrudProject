package com.example.mycrudproject.dto.mapperdto;

import com.example.mycrudproject.dto.FeedDTO;
import com.example.mycrudproject.dto.mapperinterface.DtoMapper;
import com.example.mycrudproject.entity.Feed;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FeedDtoMapper implements DtoMapper<FeedDTO, Feed> {
    private final ModelMapper modelMapper = new ModelMapper();

    public Feed convertToModel(FeedDTO feedDTO) {
        return modelMapper.map(feedDTO, Feed.class);
    }

    public FeedDTO convertToDTO(Feed feed) {
        return modelMapper.map(feed, FeedDTO.class);
    }
}
