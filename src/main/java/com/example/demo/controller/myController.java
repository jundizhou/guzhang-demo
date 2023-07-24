package com.example.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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
    public String accessUrl(@RequestParam String url) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(10_000, TimeUnit.MILLISECONDS)
                .build();

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Response response = httpClient.newCall(request).execute();

            int responseCode = response.code();
            String responseBody = response.body() != null ? response.body().string() : "";

            response.close();

            // You can replace "logger" with your actual logger instance
            logger.info("Accessed URL: " + url);
            logger.info("Response Code: " + responseCode);
            logger.info("Response Body: " + responseBody);

            return responseBody;
        } catch (IOException e) {
            // You can replace "logger" with your actual logger instance
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

