package com.egorka.lr2v1.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(public void handleRequest(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Вызов метода: " + joinPoint.getSignature().getName());
    }

    @After("execution(public void handleRequest(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Метод выполнен: " + joinPoint.getSignature().getName());
    }
}