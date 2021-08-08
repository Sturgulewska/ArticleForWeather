package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherCurrentConditionDto {

    @JsonProperty("LocalObservationDateTime")
    private String localObservationDateTime;

   @JsonProperty("EpochTime")
   private Long epochTime;

    @JsonProperty("WeatherText")
   private String weatherText;

    @JsonProperty("WeatherIcon")
    private Integer weatherIcon;

    @JsonProperty("HasPrecipitation")
    private Boolean hasPrecipitation;

    @JsonProperty("PrecipitationType")
    private String precipitationType;

    @JsonProperty("IsDayTime")
    private Boolean isDayTime;

    @JsonProperty("Temperature")
    private WeatherTemperatureDto temperature;

    @JsonProperty("MobileLink")
    private String mobileLink;

    @JsonProperty("Link")
    private String link;
}
