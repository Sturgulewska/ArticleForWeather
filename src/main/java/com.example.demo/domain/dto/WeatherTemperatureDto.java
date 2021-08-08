package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherTemperatureDto {

    @JsonProperty("Metric")
    private WeatherTemperatureDetailsDto metricTemperature;

    @JsonProperty("Imperial")
    private WeatherTemperatureDetailsDto imperialTemperature;
}
