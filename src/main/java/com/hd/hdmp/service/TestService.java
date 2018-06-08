package com.hd.hdmp.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author fanzhenxing
 * @create 2018/6/6 4:35 PM
 */
@Component
public class TestService {
    @Cacheable("gsm-message-cache")
    public String  getTestData(String key){
        System.out.println("execute me ....................");
        return "aaaaaaaaa";
    }
}
