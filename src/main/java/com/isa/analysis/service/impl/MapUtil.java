package com.isa.analysis.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hexu on 2017/1/3.
 */
@Service
public class MapUtil {

    public Map<String, Object> map(String key1, Object value1){
        Map<String, Object> result = new HashMap<>();
        result.put(key1, value1);
        return result;
    }

    public Map<String, Object> map(String key1, Object value1, String key2, Object value2){
        Map<String, Object> result = new HashMap<>();
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }

    public Map<String, Object> map(String key1, Object value1, String key2, Object value2, String key3, Object value3){
        Map<String, Object> result = new HashMap<>();
        result.put(key1, value1);
        result.put(key2, value2);
        result.put(key3, value3);
        return result;
    }

    public Map<String, Object> map(String key1, Object value1, String key2, Object value2, String key3, Object value3, String key4, Object value4){
        Map<String, Object> result = new HashMap<>();
        result.put(key1, value1);
        result.put(key2, value2);
        result.put(key3, value3);
        result.put(key4, value4);
        return result;
    }
}
