package com.example.demo.service;

import com.example.demo.configuration.NYTConfiguration;
import com.example.demo.domain.dto.NYTAdditionalParameters;
import com.example.demo.domain.dto.NYTDto;
import com.example.demo.service.utils.MyJsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NYTService {
    private final NYTConfiguration NYTConfiguration;

    public NYTDto getNYT(NYTAdditionalParameters additionalParameters) throws JsonProcessingException {
        Request request = new Request.Builder()
                .url(createHost(additionalParameters))
                .build();

        String response = executeRequest(request);
        NYTDto result = MyJsonUtils.convertStringToObject(response, NYTDto.class);
        return result;
    }

    public NYTService(NYTConfiguration NYTConfiguration) {
        this.NYTConfiguration = NYTConfiguration;
    }

    private HttpUrl createHost(NYTAdditionalParameters additionalParameters) {
        HttpUrl.Builder builder = new HttpUrl.Builder()
                .scheme("https")
                .host(NYTConfiguration.getUrl())
                .addPathSegment("svc")
                .addPathSegment("search")
                .addPathSegment("v2")
                .addPathSegment("articlesearch" + ".json")
                .addQueryParameter("api-key", NYTConfiguration.getApiKey());

        if (additionalParameters != null) {
            setAdditionalParameters(builder, additionalParameters);
        }

        return builder.build();
    }

    private void setAdditionalParameters(HttpUrl.Builder builder, NYTAdditionalParameters additionalParameters) {
        String filterQuery = additionalParameters.getFilterQuery();
        if(!StringUtils.isBlank(filterQuery)) {
            builder.addQueryParameter("fq", filterQuery);
        }
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
