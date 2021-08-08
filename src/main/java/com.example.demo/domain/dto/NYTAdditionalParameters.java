package com.example.demo.domain.dto;

import lombok.Getter;

@Getter
public class NYTAdditionalParameters {
    private String filterQuery;

    public void setSectionName(String sectionName) {
        // section_name:("Fashion & Style")
        filterQuery = "section_name:(\"" + sectionName + "\")";
    }
}
