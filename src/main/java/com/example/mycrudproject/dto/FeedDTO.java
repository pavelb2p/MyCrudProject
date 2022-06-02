package com.example.mycrudproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedDTO {

    @JsonProperty("recipeId")
    private String id;

    @NotBlank(message = "DisplayName shouldn't be empty")
    @Size(min = 3, message = "DisplayName should be more then 3 characters")
    private String displayName;

    @NotBlank(message = "Image shouldn't be empty")
    @URL
    private String images;
    private String flag;

    @NotBlank(message = "SourceDisplayName shouldn't be empty")
    private String sourceDisplayName;

    @NotBlank(message = "SourceSiteUrl shouldn't be empty")
    private String sourceSiteUrl;

    @NotBlank(message = "Description shouldn't be empty")
    private String description;
}
