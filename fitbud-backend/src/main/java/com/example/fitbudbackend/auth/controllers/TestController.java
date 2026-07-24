package com.example.fitbudbackend.auth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// Note: I used this controller in order to test with Postman
// calling a protected endpoint with and without a token

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String test() {
        return "You are authenticated!";
    }

}
