package com.example.newscall.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

//    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
//
//    @AfterReturning(
//            pointcut = "execution(* com.example.newssource.service.NewsScheduler.getData(..))", returning = "result")
//    public void logAfterNewsFetch(Object result) {
//        if (result instanceof List<?> list) {
//            logger.info("✅ News fetched successfully with {} articles\", list.size()");
//        } else {
//            logger.info("✅ News fetched successfully: {}");
//        }
//    }
}
