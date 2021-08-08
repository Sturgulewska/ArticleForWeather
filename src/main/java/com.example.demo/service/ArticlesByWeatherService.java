package com.example.demo.service;

import com.example.demo.configuration.EmailConfiguration;
import com.example.demo.domain.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.swing.*;

@Service
public class ArticlesByWeatherService {

    private final AccuWeatherService accuWeatherService;
    private final EmailService emailService;
    private final NYTService nytService;
    private final EmailConfiguration emailConfiguration;


    public ArticlesByWeatherService(AccuWeatherService accuWeatherService,
                                    EmailService emailService, NYTService nytService,
                                    EmailConfiguration emailConfiguration) {
        this.accuWeatherService = accuWeatherService;
        this.emailService = emailService;
        this.nytService = nytService;
        this.emailConfiguration = emailConfiguration;
    }

    public NYTDto getArticlesByWeather() throws JsonProcessingException {
        String documentType = getDocumentTypeForArticle();
        NYTAdditionalParameters additionalParameters = new NYTAdditionalParameters();
        additionalParameters.setSectionName(documentType);

        NYTDto articlesDto = nytService.getNYT(additionalParameters);
        return articlesDto;
    }

    private String getDocumentTypeForArticle() throws JsonProcessingException {
        WeatherCurrentConditionDto weatherDto = accuWeatherService.getActualWeather();
        Double temperature = weatherDto.getTemperature().getMetricTemperature().getValue();
        String documentType = "Science";
        if (temperature < 25) {
            documentType = "Fashion & Style";
        }

        return documentType;
    }

    public void sendConfirmMail(NYTDto nytDto) throws MessagingException, JsonProcessingException {
        String email = emailConfiguration.getEmailSender();
        WeatherCurrentConditionDto weatherDto = accuWeatherService.getActualWeather();
        NYTDto resultDto = nytService.getNYT(null);
        String body = " ";
        body = "Dzisiejsza temperatura wynosi :" + weatherDto.getTemperature().getMetricTemperature().getValue() + "\n";

        for(NYTDocDto n : resultDto.getResponse().getDoc()){
            body += "Link do artukułu" + " <a href='"+ n.getWebUrl() + "' target='_blank'>" + n.getWebUrl() + "</a>" +"\n"+ "o kategorii" +" "+ n.getSubsectionName() +"\n"+
                    "którego publikacja ukazała się" +" "+ n.getPubDate() +"\n";
        }

        String tittle = "Artykuł do dzisiejszej pogody";
        emailService.sendEmail(email, tittle, body);
    }

}
