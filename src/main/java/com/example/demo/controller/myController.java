package com.example.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.nio.channels.SocketChannel;

import static java.lang.Thread.sleep;

@RestController
public class myController {
    private final static Logger logger = LoggerFactory.getLogger(myController .class);
    @RequestMapping("/hello")
    public String firstFromWebsite(){
        logger.info("hello,spring boot!xxxxxxxxxxxxxxxxxxxxxxxxxx");
        logger.info("hello,exit  spring boot!xxxxxxxxxxxxxxxxxxxxxxxxxx");
        return "hello,spring boot!";
    }

    @RequestMapping("/access-url")
    public String accessUrl(String url) {
        try {
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();

            logger.info("Accessed URL: " + url);
            logger.info("Response Code: " + responseCode);
            logger.info("Response Body: " + response.toString());

            return response.toString();
        } catch (Exception e) {
            logger.error("Error accessing URL: " + url, e);
            return "Error accessing URL: " + url;
        }
    }

    @RequestMapping("/sleepTime")
    public String sleepTime(int sleepTime){
        logger.info("sleep start!");
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("sleep end");
        return "hello,spring boot!";
    }
}

