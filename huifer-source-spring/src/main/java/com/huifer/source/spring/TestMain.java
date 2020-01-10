package com.huifer.source.spring;

import org.springframework.beans.factory.BeanExpressionException;

public class TestMain {
    public static void main(String[] args) {
        BeanExpressionException test = new BeanExpressionException("中文测试");
        System.out.println(test.getMessage());
    }
}
