package com.example.demo.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class AccuWeatherConfiguration {
    @Value("${api.accuweather.apikey}")
    private String apiKey;

    @Value("${api.accuweather.location_id}")
    private String locationId;

    @Value("${api.accuweather.url}")
    private String url;
}
