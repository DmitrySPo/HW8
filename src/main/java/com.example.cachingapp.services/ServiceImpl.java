package com.example.cachingapp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {
    @Override
    public List<String> run(String item, double value, Date date) {
        // Реализация сложного метода
        return new ArrayList<>();
    }

    @Override
    public List<String> work(String item) {
        // Реализация другого метода
        return new ArrayList<>();
    }
}
