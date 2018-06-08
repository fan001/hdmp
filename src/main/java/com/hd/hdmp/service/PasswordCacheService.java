package com.hd.hdmp.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fanzhenxing
 * @create 2018/6/7 10:50 AM
 */
@Component
public class PasswordCacheService {

   /* @CachePut("")
    public AtomicInteger putValue(String username){

    }


    @Cacheable("passwordRetryCache")
    public AtomicInteger getValue(String username){
        return new AtomicInteger(0);

    }
    @CacheEvict
    public void evictPassword(String username){

    }*/
}
