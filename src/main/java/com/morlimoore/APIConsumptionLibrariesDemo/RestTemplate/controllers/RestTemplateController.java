package com.morlimoore.APIConsumptionLibrariesDemo.RestTemplate.controllers;

import com.morlimoore.APIConsumptionLibrariesDemo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class RestTemplateController {

    private final String BASE_URL = "http://localhost:8080/api/v1/users";
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    RestTemplate restTemplate;

    //Using the GET method
    @RequestMapping("get/users")
    public String getUsers() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String response = restTemplate.exchange(BASE_URL + "/all", HttpMethod.GET, entity, String.class).getBody();
        return response;
    }

    //Using the POST method
    @RequestMapping(value = "post/user", method = RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        String response = restTemplate.exchange(BASE_URL + "/add", HttpMethod.POST, entity, String.class).getBody();
        return response;
    }

    //Using the PUT method
    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable("id") String id, @RequestBody User user) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        String response = restTemplate.exchange(BASE_URL + "/update/" + id, HttpMethod.PUT, entity, String.class).getBody();
        return response;
    }

    //Using the DELETE method
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") String id) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(headers);
        String response = restTemplate.exchange(BASE_URL + "/delete/" + id, HttpMethod.DELETE, entity, String.class).getBody();
        return response;
    }
}
