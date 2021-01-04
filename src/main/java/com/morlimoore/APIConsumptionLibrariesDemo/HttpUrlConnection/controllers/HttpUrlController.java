package com.morlimoore.APIConsumptionLibrariesDemo.HttpUrlConnection.controllers;

import com.morlimoore.APIConsumptionLibrariesDemo.models.User;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class HttpUrlController {

    private final String BASE_URL = "http://localhost:8080/api/v1/users";
    private final String USER_AGENT = "Mozilla/5.0";

    @RequestMapping("httpurl/get/users")
    public String getUsers() throws IOException {
        URL obj = new URL(BASE_URL + "/all");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        return "An error occurred";
    }

    @RequestMapping(value = "httpurl/post/user", method = RequestMethod.POST)
    public String postUser(@RequestBody User user) throws IOException {
        URL obj = new URL(BASE_URL + "/add");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = user.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        int responseCode = con.getResponseCode();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }

    @RequestMapping(value = "httpurl/update/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable("id") String id, @RequestBody User user) throws IOException {
        URL obj = new URL(BASE_URL + "/update/" + id);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = user.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        int responseCode = con.getResponseCode();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }

    @RequestMapping(value = "httpurl/delete/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") String id) throws IOException {
        URL obj = new URL(BASE_URL + "/delete/" + id);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        int responseCode = con.getResponseCode();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }
}
