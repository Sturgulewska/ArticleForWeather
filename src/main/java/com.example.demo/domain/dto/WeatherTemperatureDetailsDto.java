package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherTemperatureDetailsDto {
    @JsonProperty("Value")
    private Double value;

    @JsonProperty("Unit")
    private String unit;

    @JsonProperty("UnitType")
    private Integer unityType;
}
