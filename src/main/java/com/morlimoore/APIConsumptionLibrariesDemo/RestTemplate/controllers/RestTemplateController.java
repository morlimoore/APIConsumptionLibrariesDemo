package com.morlimoore.APIConsumptionLibrariesDemo.RestTemplate.controllers;

import com.morlimoore.APIConsumptionLibrariesDemo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;

import static com.morlimoore.APIConsumptionLibrariesDemo.utils.ResponseUtil.createResponse;

@RestController
public class RestTemplateController {

    private final String BASE_URL = "http://localhost:8080/api/v1/users";
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    RestTemplate restTemplate;

    //Using the GET method
    @RequestMapping(value = "resttemplate/get/users", method = RequestMethod.GET)
    public ResponseEntity<String> getUsers() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String response = restTemplate.exchange(BASE_URL + "/all", HttpMethod.GET, entity, String.class).getBody();
        return createResponse(HttpStatus.OK, response);
    }

    //Using the POST method
    @RequestMapping(value = "resttemplate/post/user", method = RequestMethod.POST)
    public ResponseEntity<String> postUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return createResponse(HttpStatus.BAD_REQUEST,
                    bindingResult.getFieldError().getDefaultMessage());

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        String response = restTemplate.exchange(BASE_URL + "/add", HttpMethod.POST, entity, String.class).getBody();
        return createResponse(HttpStatus.CREATED, response);
    }

    //Using the PUT method
    @RequestMapping(value = "resttemplate/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable("id") String id, @RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return createResponse(HttpStatus.BAD_REQUEST,
                    bindingResult.getFieldError().getDefaultMessage());

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        String response = restTemplate.exchange(BASE_URL + "/update/" + id, HttpMethod.PUT, entity, String.class).getBody();
        return createResponse(HttpStatus.OK, response);
    }

    //Using the DELETE method
    @RequestMapping(value = "resttemplate/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(headers);
        String response = restTemplate.exchange(BASE_URL + "/delete/" + id, HttpMethod.DELETE, entity, String.class).getBody();
        return createResponse(HttpStatus.OK, response);
    }
}