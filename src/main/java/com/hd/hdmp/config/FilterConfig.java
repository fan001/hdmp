package com.hd.hdmp.config;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * @author fanzhenxing
 * @create 2018/5/29 9:49 PM
 */
@Configuration
public class FilterConfig {

  @Bean
    public FilterRegistrationBean shiroFilterRegisteration(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
        registrationBean.addInitParameter("targetFilterLifecycle","true");
        registrationBean.setEnabled(true);
        registrationBean.setOrder(Integer.MAX_VALUE-1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }






}
