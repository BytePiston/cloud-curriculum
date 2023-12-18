package com.cactus.cloud.curriculum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class HelloWorldController {

    @GetMapping(path = "/hello")
    public String helloWorld(){
        return "Hello World!";
    }
}
