package com.morlimoore.APIConsumptionLibrariesDemo.HttpUrlConnection.controller;

import com.morlimoore.APIConsumptionLibrariesDemo.models.User;
import com.morlimoore.APIConsumptionLibrariesDemo.utils.ConnectionUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.morlimoore.APIConsumptionLibrariesDemo.utils.ResponseUtil.createResponse;

@RestController
@AllArgsConstructor
public class HttpUrlController {

    ConnectionUtil connectionUtil;

    private final String BASE_URL = "http://localhost:8080/api/v1/users";

    @RequestMapping(value = "httpurl/get/users", method = RequestMethod.GET)
    public ResponseEntity<String> getUsers() throws IOException {
        URL obj = new URL(BASE_URL + "/all");
        HttpURLConnection con = connectionUtil.getHttpConnection(obj, "GET");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return createResponse(HttpStatus.OK, response.toString());
        }
        return createResponse(HttpStatus.BAD_REQUEST, "An error occurred");
    }

    @RequestMapping(value = "httpurl/post/user", method = RequestMethod.POST)
    public ResponseEntity<String> postUser(@RequestBody @Valid User user, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors())
            return createResponse(HttpStatus.BAD_REQUEST,
                    bindingResult.getFieldError().getDefaultMessage());

        URL obj = new URL(BASE_URL + "/add");
        HttpURLConnection con = connectionUtil.getHttpConnection(obj, "POST");
        connectionUtil.writeToOutputStream(con, user);
        String response = connectionUtil.getAPIResponse(con, user);
        return createResponse(HttpStatus.CREATED, response);
    }

    @RequestMapping(value = "httpurl/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable("id") String id, @RequestBody User user) throws IOException {
        URL obj = new URL(BASE_URL + "/update/" + id);
        HttpURLConnection con = connectionUtil.getHttpConnection(obj, "PUT");
        connectionUtil.writeToOutputStream(con, user);
        String response = connectionUtil.getAPIResponse(con, user);
        return createResponse(HttpStatus.OK, response);
    }

    @RequestMapping(value = "httpurl/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) throws IOException {
        URL obj = new URL(BASE_URL + "/delete/" + id);
        HttpURLConnection con = connectionUtil.getHttpConnection(obj, "DELETE");
        String response = connectionUtil.getAPIResponse(con, null);
        return createResponse(HttpStatus.OK,response);
    }
}