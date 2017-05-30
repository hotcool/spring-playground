package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/bilibala")
    public String helloWorld() {
        return "Hello from Spring!";
    }

}