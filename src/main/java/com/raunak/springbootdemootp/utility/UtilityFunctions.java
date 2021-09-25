package com.raunak.springbootdemootp.utility;

import java.util.Map;

public class UtilityFunctions {
    public static <T> T getNestedValue(Map map, String... keys) {
        Object value = map;

        for (String key : keys) {
            value = ((Map) value).get(key);
        }

        return (T) value;
    }

    public void playground(){
        System.out.println("This fn can be used by putting a debug on this statement, and then run snippets of code.");
    }
}
