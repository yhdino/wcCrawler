/*****************************************************
 * Copyright(C):智汇神州信息发展有限公司
 * 文件名称	    :JsonUtils.java
 * 编制人员     :han
 * 创建日期 	:2013-01-29
 * 版本		    :v1.0
 *
 *修改记录
 *	版本信息     ：
 *	更改人员     ：
 *  更改日期     ：
 *  更改内容     ：
 *  更改原因     ：
 ****************************************************/
package com.zhsz.utils.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;



public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * javaBean,list,array convert to json string
     */
    public static String obj2json(Object obj) throws Exception{
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr,Class<T> clazz) throws Exception{
        return objectMapper.readValue(jsonStr, clazz);
    }

    /**
     * json string convert to map
     */
    public static <T> Map<String,Object> json2map(String jsonStr)throws Exception{
        return objectMapper.readValue(jsonStr, Map.class);
    }

    /**
     * map convert to javaBean
     */
    public static <T> T map2pojo(Map map,Class<T> clazz){
        return objectMapper.convertValue(map, clazz);
    }

    public static String map2json(Map map) throws JsonProcessingException {
        return new String(objectMapper.writeValueAsString(map));
    }
}