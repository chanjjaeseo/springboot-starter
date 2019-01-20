package com.springboot.demo.annotation;

import com.springboot.demo.exception.BizException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ExceptionCatcherAspect {

    private Logger logger = LoggerFactory.getLogger(ExceptionCatcherAspect.class);

    @Pointcut("execution(* com.springboot.demo.service.TestService.*(..))")
    private void pointcut() {}

    @Around("pointcut()")
    public void handlerException(ProceedingJoinPoint pjp) {
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            if (throwable instanceof BizException) {
                handlerBizException((BizException) throwable);
            } else if (throwable instanceof Exception) {
                handlerException((Exception) throwable);
            }
            //  do nothing in error mode
        }
    }

    private void handlerBizException(BizException e) {
        logger.error("BizException of {} in {}", e.getStackTrace(), e.getMessage(), e);
    }

    private void handlerException(Exception e) {
        logger.error("UnexpectedException of {} in {}", e.getStackTrace(), e.getMessage(), e);
    }



}
