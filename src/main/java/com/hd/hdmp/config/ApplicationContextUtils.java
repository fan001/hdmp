package com.hd.hdmp.config;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author fanzhenxing
 * @create 2018/6/7 11:10 AM
 */
@Component
@EnableAutoConfiguration
public class ApplicationContextUtils implements ApplicationContextAware {

   public static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("设置 application sucessful .......$$$$$$$$$$$$$$$$4");

    }
}
