package com.example.cachingapp.core;

import com.example.cachingapp.annotations.CacheType;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {
    private final Map<CacheKey, Object> inMemoryCache = new ConcurrentHashMap<>();
    private final File rootDirectory;

    public CacheManager(File rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public void put(CacheKey key, Object result) throws Exception {
        if (key.getCacheType() == CacheType.IN_MEMORY) {
            inMemoryCache.put(key, result);
        } else {
            saveToFile(key, result);
        }
    }

    public Object get(CacheKey key) throws Exception {
        if (key.getCacheType() == CacheType.IN_MEMORY) {
            return inMemoryCache.get(key);
        } else {
            return loadFromFile(key);
        }
    }

    private void saveToFile(CacheKey key, Object result) throws Exception {
        // Логика записи объекта в файл
    }

    private Object loadFromFile(CacheKey key) throws Exception {
        // Логика чтения объекта из файла
        return null; // Возвращение значения из файла
    }
}
