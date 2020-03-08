package com.tala.kafkaingress.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String main()
    {
        return "Welcome To Kafka-Ingress Demo!";
    }
}
