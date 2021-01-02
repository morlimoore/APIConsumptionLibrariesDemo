package com.morlimoore.resttemplatedemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class ConsumeWebService {

    private final String BASE_URL = "http://localhost:8080/api/v1/";

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("get/users")
    public String getUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String response = restTemplate.exchange(BASE_URL + "users/all", HttpMethod.GET, entity, String.class).getBody();
        return response;
    }
}
