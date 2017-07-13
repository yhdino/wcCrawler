package com.zhsz.wechat.tools;

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
            System.out.println("serviceConf.properties not found /config.properties in CLASSPATH.");
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

    public static void main(String[] args) {
//        System.out.println(getPropertiesValue("jdbc.username"));
        System.out.println(Class.class.getClass().getResource("/").getPath());
    }
}
