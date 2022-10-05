package com.stussy.stussyclone20220929HDH.aop;


import com.stussy.stussyclone20220929HDH.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220929HDH.dto.CMRespDto;
import jdk.jfr.Category;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
    private void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();

        BeanPropertyBindingResult bindingResult = null;

        for( Object arg : args ){
            if(args.getClass() == BeanPropertyBindingResult.class){
                bindingResult = (BeanPropertyBindingResult) args;
                log.info("바인딩리절트 객체입니다.");
                break;
            }else{
                log.info("바인딩리절트 객체가 아닙니다.");
            }
        }

        if(bindingResult == null){
            return joinPoint.proceed();
        }

        if(bindingResult.hasErrors()){
            log.error("유효성 검사 오류 발생");
            Map<String, String> errorMap = new HashMap<String, String>();

            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(),error.getDefaultMessage());
            });
//            log.error("error: {}", errorMap);

            return ResponseEntity.badRequest().body(new CMRespDto<>(-1,"유효성 검사 실패", errorMap));
        }

        return null;
    }
}
