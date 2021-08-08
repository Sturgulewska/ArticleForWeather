package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
public class NYTDto {

    @JsonProperty("copyright")
    private String copyright;

    @JsonProperty("response")
    private NYTResponseDto response;

    @JsonProperty("keywords")
    private NYTKeywordsDto keywordsDto;
}
