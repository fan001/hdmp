package com.hd.hdmp.controller;

import com.hd.hdmp.common.annotation.SysLog;
import com.hd.hdmp.config.ApplicationContextUtils;
import com.hd.hdmp.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fanzhenxing
 * @create 2018/5/29 11:15 AM
 */
@RestController
@Api(value = "测试控制器", description = "asdasasda")
public class TestController extends BaseController {


    @Autowired
    TestService testService;

    @Autowired
    EhCacheCacheManager ehCacheCacheManager;

    @GetMapping("/test")
    @SysLog("测试控制器")
    public String getTest() {
        System.out.println(testService.getTestData("aaa"));
        Cache cache = ehCacheCacheManager.getCache("gsm-message-cache");
        System.out.println("cache value is:" + cache.get("aaa1"));
        cache.put("aaa","1234567");
        return "test";
    }

}
