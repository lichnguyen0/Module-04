package com.example.md4b9bt2.aspect;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @After("execution(* com.example.md4b9bt2.service.BorrowService.*(..))")
    public void logAction(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("[BOOK LOG] " + joinPoint.getSignature().getName() +
                " - Param: " + (args.length > 0 ? args[0] : "none"));
    }
}

