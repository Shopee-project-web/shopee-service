package com.shoppify.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class TestController {
    @GetMapping("/ping")
    public String hello(){
        return "Hello";
    }
}
