package com.example.cachingapp.services;

import com.example.cachingapp.annotations.Cache;
import com.example.cachingapp.annotations.CacheType;

import java.util.Date;
import java.util.List;

public interface Service {
    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "data", zip = true, identityBy = {String.class, double.class})
    List<String> run(String item, double value, Date date);

    @Cache(cacheType = CacheType.IN_MEMORY, maxListSize = 100_000)
    List<String> work(String item);
}

