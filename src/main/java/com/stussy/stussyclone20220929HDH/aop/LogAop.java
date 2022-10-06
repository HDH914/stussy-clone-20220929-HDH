package com.stussy.stussyclone20220929HDH.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAop {

    @Pointcut("@annotation(com.stussy.stussyclone20220929HDH.aop.annotation.LogAspect)")
    private void annotationPointCut(){}

//    @Pointcut("execution(* com.stussy.stussyclone20220929HDH.controller.api.*.*(..))")
//    private void executionPointCut(){}

    @Around("annotationPointCut()")  //@Around("실행할 메소드 명()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();  //joinPoint.getSignature(): 클래스의 정보
        String[] argNames = codeSignature.getParameterNames();

        StringBuilder argNameString = new StringBuilder();
        StringBuilder argDataString = new StringBuilder();
        for(int i = 0; i < args.length; i++){
            argNameString.append(argNames[i]);
            argDataString.append(args[i]);
            if(i < args.length - 1){
                argNameString.append(", ");
                argDataString.append(", ");
            }
        }
        log.info("Method Call -- {}.{}({}}) >> {}",  // "클래스명.메소드(파라미터)"
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                argNameString.toString(),
                argDataString.toString());

        Object result = joinPoint.proceed();

        log.info("Method Return -- {}.{}({}}) >> {}",  // "클래스명.메소드(파라미터)"
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                argNameString.toString(),
                result);

        return result;
    }
}
