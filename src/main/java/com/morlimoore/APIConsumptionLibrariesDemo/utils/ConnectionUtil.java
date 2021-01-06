package com.morlimoore.APIConsumptionLibrariesDemo.utils;

import com.morlimoore.APIConsumptionLibrariesDemo.models.User;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ConnectionUtil {

    private final String USER_AGENT = "Mozilla/5.0";

    public HttpURLConnection getHttpConnection(URL obj, String method ) throws IOException {
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        if (!method.equals("GET"))
            con.setDoOutput(true);
        return con;
    }

    public void writeToOutputStream(HttpURLConnection con, User user) throws IOException {
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = user.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }
    }

    public String getAPIResponse(HttpURLConnection con, User user) throws IOException {
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