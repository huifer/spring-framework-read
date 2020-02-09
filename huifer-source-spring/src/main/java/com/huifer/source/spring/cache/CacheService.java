package com.huifer.source.spring.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private CacheContext<DemoCache> cacheCacheContext;


    @Cacheable(value="demoCache")
    public DemoCache get(Integer id) {
        DemoCache demoCache = cacheCacheContext.get(id);
        if (demoCache != null) {
            return demoCache;
        }
        else {
            throw new RuntimeException("not have ");
        }

    }


    public void add(Integer id, DemoCache cache) {
        cacheCacheContext.addOrUpdate(id, cache);
    }

}
