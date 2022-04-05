package com.example.mycrudproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;

@Document(collection = "feeds")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feed {

    @Id
    @JsonProperty("recipeId")
    private String id;
    private String displayName;
    private String images;
    private String flag;
    private String sourceDisplayName;
    private String sourceSiteUrl;
    private String description;
}
