package com.huifer.source.spring.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class CacheContext<T> {
    private Map<Integer, T> cacheMap = new ConcurrentHashMap<>();

    public T get(Integer key) {
        return cacheMap.get(key);
    }

    public void addOrUpdate(Integer key, T value) {
        cacheMap.put(key, value);
    }

    public void removeKey(Integer key) {
        if (cacheMap.containsKey(key)) {
            cacheMap.remove(key);
        }
    }
    public void clean(){
        cacheMap.clear();
    }
}
