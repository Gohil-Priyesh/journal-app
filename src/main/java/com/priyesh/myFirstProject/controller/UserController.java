package com.priyesh.myFirstProject.controller;

import com.priyesh.myFirstProject.api.response.QuotesResponse;
import com.priyesh.myFirstProject.api.response.WeatherResponse;
import com.priyesh.myFirstProject.service.QuotesApiService;
import com.priyesh.myFirstProject.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.priyesh.myFirstProject.entity.UserEntity;
import com.priyesh.myFirstProject.repository.UserRepository;
import com.priyesh.myFirstProject.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuotesApiService quotesApiService;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity userInDb = userService.findByUserName(userName);

        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // getQuotes() should return QuotesResponse
        QuotesResponse quotesResponse = quotesApiService.getQuotes();

        String greeting = "";

        if (quotesResponse != null && quotesResponse.getData() != null && quotesResponse.getData().getData() != null) {

            List<QuotesResponse.QuoteItem> quotes = quotesResponse.getData().getData();

            if (!quotes.isEmpty()) {
                String firstQuote = quotes.get(0).getContent();
                greeting = " Today's Quote is: " + firstQuote;
            }
        }

        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }

    @GetMapping("/today's-weather")
    public ResponseEntity<?> weatherController(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Ahmedabad");
        String greeting = "";
        if(weatherResponse != null){
            greeting = ", Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting ,HttpStatus.OK);
    }

}
