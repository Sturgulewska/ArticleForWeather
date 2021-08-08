package com.example.demo.service.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MyJsonUtils {
    private final static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);
    }

    public static <T> T convertStringToObject(String jsonText, Class<T> classType) throws JsonProcessingException {
        // SimpleEntity entity = objectMapper.readValue(responseBody.string(), SimpleEntity.class);
        return objectMapper.readValue(jsonText, classType);
    }
}
