package com.stussy.stussyclone20220929HDH.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 메소드 실행 시간을 계산해주는 로직
 * */

@Slf4j
@Aspect
@Component  //IoC에 등록하려면 Component 사용
public class TimerAop {

    @Pointcut("execution(* com.stussy.stussyclone20220929HDH.controller..*.*(..))")
    private void executionPointCut(){}

    @Around("executionPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{  //joinPoint: 실행되는 메소드의 정보를 담음.
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();  //실행되는 부분

        stopWatch.stop();
        //joinPoint.getSignature(): 클래스의 정보
        //getDeclaringTypeName(): 클래스 명
        //getName(): 메소드명
        log.info("class: {}, method: {}>>> {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                stopWatch.getTotalTimeSeconds());

        return result;
    }
}
