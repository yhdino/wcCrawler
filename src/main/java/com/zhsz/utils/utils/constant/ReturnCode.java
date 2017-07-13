package com.zhsz.utils.utils.constant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

public class ReturnCode {
	private static Logger log = Logger.getLogger(ReturnCode.class);
	public static final String returnstateZ00 = "000";
	public static final String returnstateZ01 = "001";
	public static final String returnstateZ02 = "002";
	public static final String returnstateZ03 = "003";
	public static final String returnstateZ99 = "099";
	
	public static final String returnstateX1 = "1";
	public static final String returnstateX2 = "2";
	public static final String returnstateX3 = "3";
	public static final String returnstateX4 = "4";
	public static final String returnstateX5 = "5";
	public static final String returnstateX6 = "6";
	public static final String returnstateX7 = "7";
	
	public static final String returnstateX9 = "9";
	public static final String returnstateX10 = "10";
	
	public static final String returncontentZ00 = "操作成功！";
	public static final String returncontentZ01 = "操作失败！";
	public static final String returncontentZ02 = "请求超时！";
	public static final String returncontentZ03 = "请求已被篡改，不安全！";
	public static final String returncontentZ99 = "请求失败！";
	
	public static final String returncontentX1 = "";
	public static final String returncontentX2 = "库存不足";
	public static final String returncontentX3 = "订单已存在";
	public static final String returncontentX4 = "用户不存在";
	public static final String returncontentX5 = "";
	public static final String returncontentX6 = "验证码不匹配";
	public static final String returncontentX7 = "该用户已存在";
	
	public static final String returncontentX9 = "签到成功";
	public static final String returncontentX10 = "今天已签到完成,请明天继续保持呦";
	
	
	public static final String returnstateL1 = "1";
	public static final String returnstateL2 = "2";
	public static final String returncontentL1 = "原密码输入有误!";
	public static final String returncontentL2 = "密码重置成功!";
	/**
	 * oldjson--------{"":""}
	 * oldjson--------{"":[{"":""}]}
	 * @param code
	 * @param codecontent
	 * @param oldjson
	 * @return
	 */
	public static String returnjson (String code,String codecontent,String oldjson) {
		try {
			codecontent = URLEncoder.encode(codecontent, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
		}
		String json = "{\"returnstate\":\""+code+"\",\"returncontent\":\""+codecontent+"\"" + oldjson + "}";
		log.info("ReturnCode[returnjson]:"+json);
		return json;
	}
	
	public static String returnjson1 (String code,String codecontent,String list,String oldjson) {
		try {
			codecontent = URLEncoder.encode(codecontent, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
		}
		String json = "{\"returnstate\":\""+code+"\",\"returncontent\":\""+codecontent+"\""+list + oldjson + "}";
		log.info("ReturnCode[returnjson]:"+json);
		return json;
	}
	
	//此方法与returnjson1不同于 从第二个开始就是list，returncontent部分去掉了双引号
	public static String returnjson2 (String code,String codecontent,String list,String oldjson,String oldjson2) {
		
		String json = "{\"returnstate\":\""+code+"\",\"returncontent\":"+codecontent+list + oldjson + oldjson2 + "}";
		log.info("ReturnCode[returnjson]:"+json);
		return json;
	}
	
	public static String returnjsonEx(String code,String codecontent,String list,String oldjson1,String oldjson2,String oldjson3,String oldjson4,String oldjson5) {
		try {
			codecontent = URLEncoder.encode(codecontent, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
		}
		String json = "{\"returnstate\":\""+code+"\",\"returncontent\":\""+codecontent+"\""+list+oldjson1+oldjson2+oldjson3+oldjson4+oldjson5+"}";
		log.info("ReturnCode[returnjson]:"+json);
		return json;
	}
	
	public static String exceptionjson (String e) {
		String json = "{\"returnstate\":\""+ReturnCode.returnstateZ01+"\",\"returncontent\":\""+ReturnCode.returnstateZ01+"\"," +
				"\"e\":" + e + ",\"list\":[]}";
		log.info("ReturnCode[exceptionjson]:"+json);
		return json;
	}
}
