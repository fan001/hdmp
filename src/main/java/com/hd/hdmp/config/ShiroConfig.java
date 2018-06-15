package com.hd.hdmp.config;

import com.hd.hdmp.auth.FormAuthenticationExtendFilter;
import com.hd.hdmp.auth.RetryLimitHashedCredentialsMatcher;
import com.hd.hdmp.auth.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fanzhenxing
 * @create 2018/5/29 9:53 PM
 */
@Configuration
public class ShiroConfig {

    @Bean("sessionManager")
    public SessionManager sessionManager() {

        JavaUuidSessionIdGenerator sessionIdGenerator = new JavaUuidSessionIdGenerator();

        SimpleCookie sessionIdCookie = new SimpleCookie("hdmpid");
        sessionIdCookie.setHttpOnly(true);
        sessionIdCookie.setMaxAge(-1);
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(sessionIdGenerator);
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(30*1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setSessionValidationInterval(20*1000);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    @Bean
    public FormAuthenticationFilter formAuthenticationFilter() {
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationExtendFilter();
        formAuthenticationFilter.setLoginUrl("/login");
        formAuthenticationFilter.setUsernameParam("username");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setSuccessUrl("/test");
        return formAuthenticationFilter;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(UserRealm userRealm, SessionManager sessionManager, EhCacheCacheManager ehCacheCacheManager) {
        SpringCacheManagerWrapper springCacheManagerWrapper = new SpringCacheManagerWrapper();
        springCacheManagerWrapper.setCacheManager(ehCacheCacheManager);
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(springCacheManagerWrapper);
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, FormAuthenticationFilter formAuthenticationFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        //oauth过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", formAuthenticationFilter);
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "authc");
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;

    }

    @Bean
    public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher(EhCacheCacheManager ehCacheCacheManager) {
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher();
        retryLimitHashedCredentialsMatcher.setEhCacheCacheManager(ehCacheCacheManager);
        retryLimitHashedCredentialsMatcher.setHashIterations(2);
        retryLimitHashedCredentialsMatcher.setHashAlgorithmName("MD5");
        retryLimitHashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return retryLimitHashedCredentialsMatcher;
    }


    @Bean
    public UserRealm userRealm(RetryLimitHashedCredentialsMatcher credentialsMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher);
        userRealm.setCachingEnabled(false);
        return userRealm;

    }

    /**
     * 处理shiro 生命周期使用
     *
     * @return
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * AOP式方法级权限检查
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * 配置shiro 支持权限控制
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


}
