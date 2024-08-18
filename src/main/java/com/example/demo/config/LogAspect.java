package com.example.demo.config;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 以 controller 包下定义的所有请求为切入点
     */
    @Pointcut("execution(public * com.example.demo.controller..*.*(..)) ")
    public void webLog() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        try {
            MethodSignature sign = (MethodSignature) joinPoint.getSignature();
            Method method = sign.getMethod();
            //获取方法上的注解
           /* NotLog annotation = method.getAnnotation(NotLog.class);
            if (annotation == null) {
                log.info("跳过日志 {}", joinPoint.getSignature().getName());
                return;
            }*/

            // 开始打印请求日志
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            // 打印请求相关参数
            log.info("========================================== Start ==========================================");
            // 打印请求 url
            log.info("URL            : {}", request.getRequestURL().toString());
            // 打印 Http method
            log.info("HTTP Method    : {}.{} ", request.getMethod(), method.getName());
            // 打印调用 controller 的全路径以及执行方法
            log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            // 打印请求的 IP
            log.info("IP             : {}", request.getRemoteAddr());
        } catch (Throwable e) {
            log.error("前置日志切面异常", e);
        }
    }

    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        try {

            MethodSignature sign = (MethodSignature) joinPoint.getSignature();
            Method method = sign.getMethod();
            //获取方法上的注解
            NotLog annotation = method.getAnnotation(NotLog.class);
            if (annotation == null) {
                return;
            }
            log.info("=========================================== End ===========================================");
            // 每个请求之间空一行
            log.info("");
        } catch (Exception e) {
            log.error("后置日志切面异常", e);
        }
    }

//    /**
//     * 环绕
//     *
//     * @param proceedingJoinPoint
//     * @return
//     * @throws Throwable
//     */
//    @Around("webLog()")
//    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        Object result = proceedingJoinPoint.proceed();
//        // 打印出参
//        log.info("Response Args  : {}", JSONObject.toJSONString(result));
//        // 执行耗时
//        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
//        return result;
//    }


}
