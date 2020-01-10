package com.huifer.source.spring;

import com.huifer.source.spring.ann.Ubean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationApplicationSourceCode {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext aac =
                new AnnotationConfigApplicationContext("com.huifer.source.spring.ann");
        Ubean bean = aac.getBean(Ubean.class);
        System.out.println(bean);


        Ubean hc = aac.getBean("hc", Ubean.class);
        System.out.println(hc);
    }
}
