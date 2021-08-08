package com.example.demo.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class NYTConfiguration {

    @Value("${api.nytimes.api-key}")
    private String apiKey;

    @Value("${api.nytimes.url}")
    private String url;


}
