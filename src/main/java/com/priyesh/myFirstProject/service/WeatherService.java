package com.priyesh.myFirstProject.service;

import com.priyesh.myFirstProject.api.response.WeatherResponse;
import com.priyesh.myFirstProject.cache.AppCache;
import com.priyesh.myFirstProject.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    ///  This value annotation is used to get the values from the Application.yaml file
    ///  but make sure that the variable is not the static variable because static variable is associated with class
    ///  so spring will not change it because it can affect others instance of this class
    @Value("${weather.api.key}") // use dot if wheather: api: key
//    @Value("${weather_api_key}") // using the whole name
    ///  don't use static hear if I am using the @Value annotation
    private /*static*/ String apiKey;

///  I am using this api from mongo db by using the AppCache
//    private static final String API = "https://api.weatherstack.com/current?access_key=<apiKey>&query=<city>";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

public WeatherResponse getWeather(String city){
    ///  i will get the response from redis by using the below line of code
    WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
    if(weatherResponse != null){
        return weatherResponse;
    }else {
        String finalApi = appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        if(body != null){
            ///  300 means 300 seconds and the l after the 300l mean long; 300 second = 5min;
            redisService.set("weather_of_" + city,body,300l);
        }
        return body;
    }

}

}
