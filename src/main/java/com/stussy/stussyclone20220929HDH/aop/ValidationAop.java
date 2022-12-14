package com.stussy.stussyclone20220929HDH.aop;


import com.stussy.stussyclone20220929HDH.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220929HDH.dto.CMRespDto;
import com.stussy.stussyclone20220929HDH.exception.CustomValidationException;
import jdk.jfr.Category;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class ValidationAop {

    @Pointcut("@annotation(com.stussy.stussyclone20220929HDH.aop.annotation.ValidAspect)")
    private void pointCut(){}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();

        BeanPropertyBindingResult bindingResult = null;

        for(Object arg : args) {
            if(arg.getClass() == BeanPropertyBindingResult.class){
                bindingResult = (BeanPropertyBindingResult) arg;
                break;
            }
        }

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<String, String>();

            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
            throw new CustomValidationException("Validation failed", errorMap);
        }
    }

    @AfterReturning(value = "pointCut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        log.info("Validation success: {}", returnObj);
    }
}
