package com.example.cachingapp.core;

import com.example.cachingapp.annotations.Cache;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final CacheManager cacheManager;

    public CacheProxy(Object target, CacheManager cacheManager) {
        this.target = target;
        this.cacheManager = cacheManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache annotation = method.getAnnotation(Cache.class);
        if (annotation != null) {
            CacheKey key = createCacheKey(annotation, method, args);
            try {
                Object cachedResult = cacheManager.get(key);
                if (cachedResult != null) {
                    return handleList(cachedResult, annotation.maxListSize());
                }
            } catch (Exception e) {
                System.err.println("Ошибка при извлечении из кэша: " + e.getMessage());
            }

            Object result = method.invoke(target, args);
            try {
                cacheManager.put(key, result);
            } catch (Exception e) {
                System.err.println("Ошибка при сохраниении в кэш: " + e.getMessage());
            }
            return handleList(result, annotation.maxListSize());
        } else {
            return method.invoke(target, args);
        }
    }

    private CacheKey createCacheKey(Cache annotation, Method method, Object[] args) {
        List<Object> filteredArgs = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (Arrays.asList(annotation.identityBy()).contains(args[i].getClass())) {
                filteredArgs.add(args[i]);
            }
        }
        return new CacheKey(
                method,
                filteredArgs.toArray(),
                annotation.cacheType(),
                annotation.fileNamePrefix(),
                annotation.zip(),
                annotation.maxListSize()
        );
    }

    private Object handleList(Object result, int maxListSize) {
        if (result instanceof List && ((List<?>) result).size() > maxListSize) {
            return ((List<?>) result).subList(0, maxListSize);
        }
        return result;
    }

    public static <T> T cache(T target, CacheManager cacheManager) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new CacheProxy(target, cacheManager));
    }
}
