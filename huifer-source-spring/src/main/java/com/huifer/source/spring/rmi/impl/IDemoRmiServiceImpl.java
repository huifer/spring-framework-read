package com.huifer.source.spring.rmi.impl;

import com.huifer.source.spring.rmi.IDemoRmiService;

public class IDemoRmiServiceImpl implements IDemoRmiService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
