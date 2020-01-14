package com.huifer.source.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectJTest {


    @Pointcut("execution(* com.huifer.source.spring.aop.TestAopBean.test(..))")
    public void test() {
    }

    @Before("test()")
    public void beforeTest() {
        System.out.println("before test");
    }

    @After("test()")
    public void afterTest() {
        System.out.println("after test");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint joinPoint) {
        System.out.println("in around before");

        Object o = null;
        try {
            o = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("in around after");
        return o;

    }
}
