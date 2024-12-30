package com.boat.pension.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AdminAOP{
    @Pointcut("execution(* com.boat.pension.service.impl.AdminServiceImpl.*(..))")
    private void p(){}

    @Pointcut("execution(* com.boat.pension.component.WebLoginInterceptor.*(..))")
    private void p2(){}

    @Before("p()")
    public void before2(){
        System.out.println("Before adminservice");
    }

    @AfterThrowing("p()")
    public void afterThrowing2(){
        System.out.println("After throwing adminservice");
    }

    @Before("p2()")
    public void before(JoinPoint joinPoint){
        System.out.println("BEFORE INterceptor");
    }

    @AfterThrowing("p2()")
    public void afterThrowing(JoinPoint joinPoint){
        System.out.println("AFTER Throwing Interceptor, sorry to have failed");
    }
}
