package com.example.newssource.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterReturning(
            pointcut = "execution(* com.example.newssource.util.RestTemplateUtil.getData(..))", returning = "result")
    public void logAfterNewsFetch(Object result) {
        if (result instanceof List<?> list) {
            LOGGER.info("✅ News fetched successfully with {} articles", list.size());
        } else {
            LOGGER.info("✅ News fetched successfully: {}", result);
        }
    }

    @Before("execution(* com.example.newssource.service.NewsArticleServiceImpl.saveAll(..))")
    public void logBeforeSaveAll() {
        LOGGER.info("log before save in database");
    }
}
