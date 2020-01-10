package com.huifer.source.spring;

import com.huifer.source.spring.dao.HsLog;
import com.huifer.source.spring.dao.impl.HsLogDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringJDBCSourceCode {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("JDBC-demo.xml");
        HsLogDaoImpl bean = applicationContext.getBean(HsLogDaoImpl.class);
//        List<HsLog> all = bean.findAll();
//        System.out.println(all);
        HsLog hsLog = new HsLog();
        hsLog.setSource("jlkjll");
        bean.save(hsLog);

    }
}
