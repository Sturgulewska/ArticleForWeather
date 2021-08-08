package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NYTDocDto {

    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("source")
    private String source;

    @JsonProperty("pub_date")
    private String pubDate;

    @JsonProperty("document_type")
    private String documentType;

    @JsonProperty("subsection_name")
    private String subsectionName;

}
