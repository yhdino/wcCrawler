package com.zhsz.utils.utils;

import java.io.IOException;
import java.util.Map;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhsz.utils.exception.SystemException;

public class JsonUtil {

	public static String objectToJson(Object bean) {
        try {
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(bean);
            return json;
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

    public static String object2Json(Object obj) {
        return objectToJson(obj);
    }

    public static Map<?, ?> jsonToObject(String json) {
        try {
            ObjectMapper om = new ObjectMapper();
            Map<?, ?> map = om.readValue(json, Map.class);
            return map;
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    public static <T> T json2Object(String json, Class<T> clz) {
        try {
            ObjectMapper om = new ObjectMapper();
            T obj = om.readValue(json, clz);
            return obj;
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

}
