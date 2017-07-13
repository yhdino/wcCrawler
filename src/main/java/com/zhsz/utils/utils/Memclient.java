package com.zhsz.utils.utils;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhsz.utils.utils.constant.Globals;

/**
 * Created by Han on 15-3-17.
 */
public class Memclient {
    private static MemcachedClient memcachedClient = null;
    private static int timeout = Globals.DEFAULT_MEMCLIENT_TIMEOUT;
    private static Memclient instance = null;

    static {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/Xmemcached.xml");
        memcachedClient = (MemcachedClient) applicationContext.getBean("memcachedClient");
        timeout = Integer.parseInt(SysConfig.getPropertiesValue("defaultTimeOut"));
    }
    
	public static synchronized Memclient getInstance()

	{
		if (instance == null)
		{
			instance = new Memclient();
		}
		return instance;

	}
	
    

    public boolean set(String key,int secend,Object val){
        if(key.isEmpty() || null == key){
            return false;
        }else {
            /*if(secend<1){
                secend = this.timeout;
            }else {*/
                try {
                    memcachedClient.set(key,secend,val);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (MemcachedException e) {
                    e.printStackTrace();
                }
            /*}*/
        }
        return false;
    }

    public Object get(String key) {
    	Object str = null;
        try {
            str = memcachedClient.get(key);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return str;
    }

    public Boolean containKey(String key) {
        String result = null;
        if(key.isEmpty() || null == key){
            return false;
        }else {
            try {
                result = memcachedClient.get(key);
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (MemcachedException e) {
                e.printStackTrace();
            }
            return (null == result)?false:true;
        }
    }

    public MemcachedClient getMemcacheClient(){
        return this.memcachedClient;
    }

    public static void main(String[] args){
        Memclient mc = new Memclient();
        mc.set("a",1,"aa");
        System.out.println(mc.get("a"));
        System.out.println(mc.containKey("a"));
        
        try {
            Thread.sleep(2000);
            System.out.println(mc.get("a"));
            System.out.println(mc.containKey("a"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
