package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/current")
    public String HelloWorld(){
        LocalDateTime now = LocalDateTime.now();
        return "hello current is "+now.toString();
    }
}
