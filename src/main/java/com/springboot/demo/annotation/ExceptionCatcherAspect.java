package com.springboot.demo.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.exception.BizException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ExceptionCatcherAspect {

    private Logger logger = LoggerFactory.getLogger(ExceptionCatcherAspect.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Around("@annotation(ec)")
    public Object handlerException(ProceedingJoinPoint pjp, ExceptionCatcher ec) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            if (throwable instanceof BizException) {
                return handlerBizException((BizException) throwable, pjp, ec);
            } else {
                return handlerException((BizException) throwable, pjp, ec);
            }
        }
    }

    private Object handlerBizException(BizException e, ProceedingJoinPoint pjp, ExceptionCatcher ec) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        try {
            logger.error("BizException | {}.{} | param: {}", pjp.getTarget().getClass().getName(), signature.getName(), objectMapper.writeValueAsString(pjp.getArgs()), e);
        } catch (JsonProcessingException e1) {
            //
        }
        return null;
    }

    private Object handlerException(Exception e, ProceedingJoinPoint pjp, ExceptionCatcher ec) throws InstantiationException, IllegalAccessException {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        try{
            logger.error("{} | {}.{} | param: {}", e.getClass().getName(), pjp.getTarget().getClass().getName(), signature.getName(), objectMapper.writeValueAsString(pjp.getArgs()), e);
        } catch (JsonProcessingException e1) {
            //
        }
        Class returnType = signature.getReturnType();
        return returnValue(returnType, ec);
    }

    private Object returnValue(Class returnType, ExceptionCatcher ec) throws IllegalAccessException, InstantiationException {
        if (returnType == null) {
            return null;
        }

        // 返回值类型为布尔是返回false
        if (boolean.class.equals(returnType)) {
            return false;
        }

        // 返回值类型为值类型的int返回0
        if (int.class.equals(returnType)) {
            return 0;
        }

        // 其它类型则根据注解确定是否要实例化返回
        return ec.value() ? newInstance(returnType) : null;
    }

    private Object newInstance(Class aClass) throws IllegalAccessException, InstantiationException {
        if(List.class.equals(aClass)){
            return new ArrayList<>();
        }
        if(Map.class.equals(aClass)){
            return new HashMap<>();
        }
        if (Boolean.class.equals(aClass)) {
            return false;
        }
        if (Integer.class.equals(aClass)) {
            return 0;
        }
        if (BigDecimal.class.equals(aClass)) {
            return BigDecimal.ZERO;
        }
        return aClass.newInstance();
    }



}
