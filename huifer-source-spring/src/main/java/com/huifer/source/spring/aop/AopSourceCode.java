package com.huifer.source.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopSourceCode {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aop/AopDemo.xml");
        TestAopBean testAopBean = context.getBean("testAopBean", TestAopBean.class);
        testAopBean.test();
    }
}
