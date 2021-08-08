package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NYTKeywordsDto {
    @JsonProperty("name")
    private String nameKeywords;

    @JsonProperty("value")
    private String valueKeywords;

    @JsonProperty("rank")
    private Integer rankKeywords;

    @JsonProperty("major")
    private String majorKeywords;
}
