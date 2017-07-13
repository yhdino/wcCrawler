package com.zhsz.utils.utils;

import java.util.Properties;

/**
 *  读取serviceParam.properties方式配置文件
 *
 * @author han
 * @version v1.0
 */
public class SysConfig {
    private static Properties conn_props = null;
    static{
        try{
            conn_props = new Properties();
            conn_props.load(SysConfig.class.getResourceAsStream("/config.properties"));
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("serviceConf.properties not found /config.proerties in CLASSPATH.");
        }
    }

    /**
     * 获取serviceConf.properties中键的值
     * @param key 键的名称
     * @return 键的值
     */
    public static String getPropertiesValue(String key){
        Object obj = conn_props.get(key);
        if(obj != null){
            return obj.toString();
        }else{
            return "";
        }
    }
    
    public static void setPropertiesValue(String key,String value){
    	/*String mm=getPropertiesValue(key);*/
    	/*System.out.println("key"+key);
    	System.out.println("mm"+mm);
    	System.out.println("value"+value);*/
    	/*if(!mm.equals(value)){*/
    		conn_props.setProperty(key, value);
    	/*}*/
    }
    
    
    
}
