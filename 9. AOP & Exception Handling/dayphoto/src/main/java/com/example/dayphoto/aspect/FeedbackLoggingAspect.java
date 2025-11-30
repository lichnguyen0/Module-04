package com.example.dayphoto.aspect;


import com.example.dayphoto.exception.BadFeedbackException;
import com.example.dayphoto.model.Feedback;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class FeedbackLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @AfterThrowing(pointcut = "execution(* com.example.dayphoto.service.JpaFeedbackService.save(..))", throwing = "ex")
    public void logBadFeedback(JoinPoint joinPoint, BadFeedbackException ex) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof Feedback feedback) {
            logger.error("Rejected feedback! Author: {}, Comment: {}, Date: {}, Time: {}, Reason: {}",
                    feedback.getAuthor(),
                    feedback.getComment(),
                    LocalDateTime.now().toLocalDate(),
                    LocalDateTime.now().toLocalTime(),
                    ex.getMessage());
        }
    }
}

