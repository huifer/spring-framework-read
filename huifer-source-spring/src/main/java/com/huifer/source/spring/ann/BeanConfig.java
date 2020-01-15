package com.huifer.source.spring.ann;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import  org.springframework.beans.factory.config.ConfigurableBeanFactory;
@Configuration
public class BeanConfig {
    @Scope(value =ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean(value = "hc")
    @Lazy
    public Ubean f() {
        return new Ubean();
    }
}
