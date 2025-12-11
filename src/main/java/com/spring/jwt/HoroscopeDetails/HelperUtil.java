package com.spring.jwt.HoroscopeDetails;

import org.apache.logging.log4j.util.Supplier;

import java.util.function.Consumer;

public class HelperUtil {

    public static <T> void getDataIfNotNull(Supplier<T> getter, Consumer<T> setter) {
        T value = getter.get();

        if (value == null) {
            return;
        }

        // Blank check
        if (value instanceof String && ((String) value).isBlank()) {
            return;
        }
        setter.accept(value);
    }
}

