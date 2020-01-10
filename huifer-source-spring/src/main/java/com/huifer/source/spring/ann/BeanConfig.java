package com.huifer.source.spring.ann;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean(value = "hc")
    public Ubean f() {
        return new Ubean();
    }
}
