package com.example.demo.service;

import com.example.demo.configuration.AccuWeatherConfiguration;
import com.example.demo.domain.dto.WeatherCurrentConditionDto;
import com.example.demo.service.utils.MyJsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccuWeatherService {
    private final AccuWeatherConfiguration accuWeatherConfiguration;

    public WeatherCurrentConditionDto getActualWeather() throws JsonProcessingException {
        Request request = new Request.Builder()
                .url(createUrl())
                .build();

        String response = executeRequest(request);
        WeatherCurrentConditionDto result = MyJsonUtils.convertStringToObject(response, WeatherCurrentConditionDto.class);
        return result;
    }

    public AccuWeatherService(AccuWeatherConfiguration accuWeatherConfiguration) {
        this.accuWeatherConfiguration = accuWeatherConfiguration;
    }

    private HttpUrl createUrl() {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(accuWeatherConfiguration.getUrl())
                .addPathSegment("currentconditions")
                .addPathSegment("v1")
                .addPathSegment(accuWeatherConfiguration.getLocationId() + ".json")
                .addQueryParameter("apikey", accuWeatherConfiguration.getApiKey())
                .addQueryParameter("language", "pl-pl")
                .addQueryParameter("metric", "true")
                .build();

        return url;
    }

    private String executeRequest(Request request) {
        String result = "";
        OkHttpClient client = new OkHttpClient();
        Call requestCall = client.newCall(request);
        try (ResponseBody response = requestCall.execute().body()) {
            if (response != null) {
                result = response.string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
