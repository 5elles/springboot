package by.academy.springboot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.Duration;
import java.util.Date;

@Component
@Aspect
public class EnterpriseAspect {
    @Pointcut("execution(* by.academy.springboot.service.impl.*.*(..))")
    private void enterprisePointcut(){}
//    @Around("enterprisePointcut()")
//    public void doTimeMeasurement(ProceedingJoinPoint point) throws Throwable {
//        long start = new Date().getTime();
//            point.proceed();
//        long end = new Date().getTime();
//        long duration = end - start;
//        System.out.println(point.getSignature().getName() +"\nduration:"+ duration  + "ms\n");
//    }
}
