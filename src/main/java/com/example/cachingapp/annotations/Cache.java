package com.example.cachingapp.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Cache {
        /** * Тип кеша: в памяти или на диске. */
        CacheType cacheType() default CacheType.IN_MEMORY;

        /** * Префикс имени файла для сохранения данных на диск. */
        String fileNamePrefix() default "";

        /** * Использовать сжатие для файлов на диске. */
        boolean zip() default false;

        /** * Указание типов аргументов, которые влияют на уникальность результата. */
        Class<?>[] identityBy() default {};

        /** * Максимальное количество элементов в списке, если метод возвращает список. */
        int maxListSize() default Integer.MAX_VALUE;
    }

