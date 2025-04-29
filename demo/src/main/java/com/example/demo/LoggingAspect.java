package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {


    @Before("execution(* com.example.demo.EmailSender.send(..))")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.printf("[before] 메일 전송 준비");
    }

    @After("execution(* com.example.demo.EamilSender.send(..))")
    public void logAfter(JoinPoint joinPoint){
        System.out.printf("[after] 메일 전송 완료");
    }
    
}
