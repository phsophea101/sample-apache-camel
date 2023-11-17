package com.sophea.spring.camel.rest;

import java.util.HashMap;
import java.util.Map;

public class CamelUtils {
    public static Map<String, Object> toHeadersMap(Object... keyValuePairs) {
        Map<String, Object> result = new HashMap<>();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            result.put(keyValuePairs[i].toString(), keyValuePairs[i + 1]);
        }
        return result;
    }
}
