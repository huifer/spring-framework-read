package com.huifer.source.spring.cache;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CacheSourceCode {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("Cache.xml");
        CacheService bean = classPathXmlApplicationContext.getBean(CacheService.class);
        bean.add(1, new DemoCache("a"));
        System.out.println(bean.get(1));
    }
}
