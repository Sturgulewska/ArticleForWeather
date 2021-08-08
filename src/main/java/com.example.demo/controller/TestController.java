package com.example.demo.controller;

import com.example.demo.domain.dto.NYTDto;
import com.example.demo.domain.dto.WeatherCurrentConditionDto;
import com.example.demo.service.AccuWeatherService;
import com.example.demo.service.ArticlesByWeatherService;
import com.example.demo.service.EmailService;
import com.example.demo.service.NYTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RequestMapping("/test_controller")
@RestController
@CrossOrigin
public class TestController {

    private final AccuWeatherService accuWeatherService;

    private final NYTService nytService;

    private final ArticlesByWeatherService articlesByWeatherService;

    private final EmailService emailService;

    public TestController(AccuWeatherService accuWeatherService,
                          NYTService nytService,
                          ArticlesByWeatherService articlesByWeatherService, EmailService emailService) {
        this.accuWeatherService = accuWeatherService;
        this.nytService = nytService;
        this.articlesByWeatherService = articlesByWeatherService;
        this.emailService = emailService;
    }

    @RequestMapping(value = "/get_actual_weather", method = RequestMethod.GET)
    public ResponseEntity<Object> getActualWeather() throws JsonProcessingException, MessagingException {
        // String result = accuWeatherService.getActualWeather();
        WeatherCurrentConditionDto resultDto = accuWeatherService.getActualWeather();
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/get_article_NYT", method = RequestMethod.GET)
    public ResponseEntity<Object> getArticleNyt() throws JsonProcessingException {
        NYTDto resultDto = nytService.getNYT(null);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/get_article_by_weather", method = RequestMethod.GET)
    public ResponseEntity<Object> getArticleByWeather() throws JsonProcessingException {
        NYTDto resultDto = articlesByWeatherService.getArticlesByWeather();
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/send_article_by_weather_and_article", method = RequestMethod.GET)
    public ResponseEntity<Object> sendArticleByWeatherAndArticle() throws JsonProcessingException, MessagingException {
        NYTDto resultDto = articlesByWeatherService.getArticlesByWeather();
        articlesByWeatherService.sendConfirmMail(resultDto);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}

