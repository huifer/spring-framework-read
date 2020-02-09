package com.huifer.source.spring.cache;

public class DemoCache {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DemoCache() {
    }

    public DemoCache(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoCache{" +
                "name='" + name + '\'' +
                '}';
    }
}
