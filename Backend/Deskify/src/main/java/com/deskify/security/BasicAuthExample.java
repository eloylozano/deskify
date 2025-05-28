package com.deskify.security;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class BasicAuthExample {
    public static void main(String[] args) throws Exception {
        String username = "usuario";
        String password = "password";
        String urlString = "http://localhost:8080/api/resource";  

        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
    }
}
