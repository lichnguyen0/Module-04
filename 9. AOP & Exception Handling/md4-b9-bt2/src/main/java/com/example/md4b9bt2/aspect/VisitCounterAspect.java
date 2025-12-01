package com.example.md4b9bt2.aspect;



import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class VisitCounterAspect {

    private int count = 0;

    @After("execution(* com.example.md4b9bt2.controller.*.*(..))")
    public void countVisitor() {
        count++;
        System.out.println("[VISIT] Total visits: " + count);
    }
}
