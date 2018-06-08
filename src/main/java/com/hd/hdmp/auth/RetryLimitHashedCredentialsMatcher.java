package com.hd.hdmp.auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fanzhenxing
 * @create 2018/6/7 1:17 PM.
 */
@Slf4j
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {


    EhCacheCacheManager ehCacheCacheManager;

    public void setEhCacheCacheManager(EhCacheCacheManager ehCacheCacheManager) {
        this.ehCacheCacheManager = ehCacheCacheManager;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        Cache cache = ehCacheCacheManager.getCache("passwordRetryCache");
        AtomicInteger retryCount;
        if (cache.get(username) == null) {
            retryCount = new AtomicInteger(0);
            cache.put(username, retryCount);
        } else {
            retryCount = (AtomicInteger) (cache.get(username).get());
        }
        if (retryCount.incrementAndGet() > 10) {
            throw new ExcessiveAttemptsException();

        }

        boolean matches = super.doCredentialsMatch(token, info);

        if (matches) {
            log.error("match success remove cache value " + username);
            cache.evict(username);
        }

        return matches;
    }
}
