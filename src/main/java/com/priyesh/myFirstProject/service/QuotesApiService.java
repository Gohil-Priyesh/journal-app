package com.priyesh.myFirstProject.service;

import com.priyesh.myFirstProject.api.response.QuotesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuotesApiService {

    @Autowired private RestTemplate restTemplate;


    private static final String API = "https://api.freeapi.app/api/v1/public/quotes?page=1&limit=10&query=human";




    public QuotesResponse getQuotes (){
        ///  to hi api through code use restTemplate
        ///  the last parameter in this is ResponseType so we convert the json to pojo
        ///  and then give the pojo in the ResponseType in the restTemplate
        ResponseEntity<QuotesResponse> response = restTemplate.exchange(API, HttpMethod.GET, null, QuotesResponse.class);
        QuotesResponse body = response.getBody();
        return body;
    }

    public QuotesResponse forPostRequest(){


        ///  for sending anything in head in api call
        HttpHeaders httpHeaders = new HttpHeaders(); /// first make the object of http headers

        ///  for passing anything in http headers
        httpHeaders.set("key","value"); /// then I can pass this in httpEntity below

        ///  this is one way to build a post body
//        User user = User.builder().username("priyesh").password("priyesh").build();

        ///  this is another way to build post body
        String requestBody = "{\n" +
                "    \"userName\":\"priyesh2\",\n" +
                "    \"password\":\"priyesh2\"\n" +
                "}";

        ///  this object take the body which I created above, and then I can pass this in
        ///  restTemplate
        HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody,httpHeaders);

        ResponseEntity<QuotesResponse> response = restTemplate.exchange(API, HttpMethod.POST, httpEntity, QuotesResponse.class);
        QuotesResponse body = response.getBody();
        return body;
    }
}
