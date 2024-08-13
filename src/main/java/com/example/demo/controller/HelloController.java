package com.example.demo.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/current")
    public String helloWorld(){
        LocalDateTime now = LocalDateTime.now();
        return "hello current is "+ LocalDateTimeUtil.formatNormal(now);
    }
}
