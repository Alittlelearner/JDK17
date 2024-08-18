package com.example.demo.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {
    @GetMapping("/current")
    public String helloWorld(HttpServletRequest request) {
        LocalDateTime now = LocalDateTime.now();
        return "hello current is " + LocalDateTimeUtil.formatNormal(now);

    }

}
