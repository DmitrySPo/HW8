package com.example.cachingapp.services;

import com.example.cachingapp.core.CacheManager;
import com.example.cachingapp.core.CacheProxy;

import java.io.File;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        File rootDir = new File("/path/to/cache");
        CacheManager cacheManager = new CacheManager(rootDir);
        Service service = CacheProxy.cache(new ServiceImpl(), cacheManager);

        service.run("item1", 1.0, new Date()); // Первый раз считается
        service.run("item1", 1.0, new Date()); // Второй раз берется из кеша
    }
}
