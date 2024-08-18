//package com.example.demo.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Slf4j
//@Component
//public class LoggingInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 只记录GET和POST请求的参数
//        if (request.getMethod().equalsIgnoreCase("GET") || request.getMethod().equalsIgnoreCase("POST")) {
//            String queryString = request.getQueryString();
//        }
//        return true;
//    }
//}
