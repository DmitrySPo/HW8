package com.example.cachingapp.core;

import com.example.cachingapp.annotations.CacheType;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class CacheKey {
    private final Method method;
    private final Object[] args;
    private final CacheType cacheType;
    private final String fileNamePrefix;
    private final boolean zip;
    private final int maxListSize;

    public CacheKey(Method method, Object[] args, CacheType cacheType, String fileNamePrefix, boolean zip, int maxListSize) {
        this.method = method;
        this.args = args;
        this.cacheType = cacheType;
        this.fileNamePrefix = fileNamePrefix;
        this.zip = zip;
        this.maxListSize = maxListSize;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }

    public CacheType getCacheType() {
        return cacheType;
    }

    public String getFileNamePrefix() {
        return fileNamePrefix;
    }

    public boolean isZip() {
        return zip;
    }

    public int getMaxListSize() {
        return maxListSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheKey cacheKey = (CacheKey) o;
        return Arrays.equals(args, cacheKey.args) &&
                method.equals(cacheKey.method) &&
                cacheType == cacheKey.cacheType &&
                Objects.equals(fileNamePrefix, cacheKey.fileNamePrefix) &&
                zip == cacheKey.zip &&
                maxListSize == cacheKey.maxListSize;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(method, cacheType, fileNamePrefix, zip, maxListSize);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }
}
