package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NYTResponseDto {

    @JsonProperty("docs")
    private ArrayList<NYTDocDto> doc;

}
