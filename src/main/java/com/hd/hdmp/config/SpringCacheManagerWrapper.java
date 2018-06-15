package com.hd.hdmp.config;

import com.google.common.collect.Lists;
import net.sf.ehcache.Ehcache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author fanzhenxing
 * @create 2018/6/14 9:11 AM
 */
public class SpringCacheManagerWrapper implements CacheManager {


    private org.springframework.cache.CacheManager cacheManager;

    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        org.springframework.cache.Cache springCache = cacheManager.getCache(name);

        return new SpringCacheWrapper(springCache);
    }


    static class SpringCacheWrapper implements Cache {
        private org.springframework.cache.Cache springcache;

        public SpringCacheWrapper(org.springframework.cache.Cache cache) {
            this.springcache = cache;
        }

        @Override
        public Object get(Object key) throws CacheException {
            Object value = springcache.get(key);
            if (value instanceof SimpleValueWrapper) {
                return ((SimpleValueWrapper) value).get();
            }
            return value;
        }

        @Override
        public Object put(Object key, Object value) throws CacheException {
            springcache.put(key, value);
            return value;
        }

        @Override
        public Object remove(Object key) throws CacheException {
            springcache.evict(key);
            return null;
        }

        @Override
        public void clear() throws CacheException {
            springcache.clear();

        }

        @Override
        public int size() {
            if (springcache.getNativeCache() instanceof Ehcache) {
                Ehcache ehcache = (Ehcache) springcache.getNativeCache();
                return ehcache.getSize();
            }
            throw new UnsupportedOperationException("invoke spring cache abstract size method not supported");
        }

        @Override
        public Set keys() {
            if (springcache.getNativeCache() instanceof Ehcache) {
                Ehcache ehcache = (Ehcache) springcache.getNativeCache();
                return new HashSet(ehcache.getKeys());
            }
            throw new UnsupportedOperationException("invoke spring cache abstract keys method not supported");
        }

        @Override
        public Collection values() {
            if (springcache.getNativeCache() instanceof Ehcache) {
                Ehcache ehcache = (Ehcache) springcache.getNativeCache();
                List keys = ehcache.getKeys();
                if (!CollectionUtils.isEmpty(keys)) {
                    List values = Lists.newArrayList(keys.size());
                    keys.forEach(e -> {
                        values.add(e);
                    });
                    return Collections.unmodifiableList(values);
                }
            } else {
                return Collections.emptyList();
            }
            throw new UnsupportedOperationException("invoke spring cache abstract values method not supported");
        }
    }
}
