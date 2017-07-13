package com.zhsz.utils.utils.message;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhsz.utils.utils.Memclient;
import com.zhsz.utils.utils.MyX509TrustManager;
import com.zhsz.utils.utils.SysConfig;
 
/**
 * 
 * 
 * @author hao
 * 
 */
public class MsgMoBanUtil {
	private static Logger log = LoggerFactory.getLogger(MsgMoBanUtil.class);
	private static String SENDMESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	/**
	 * 预约成功通知
	 * @param first
	 * @param orderSn
	 * @param orderStatus
	 * @param remark
	 * @param openid
	 * @param url
	 */
	public static void doSendMsgToObjYY(String title,String openid, String url,String yycontent_str,String yytime_str,String remark){
		MessageVo msgVo = new MessageVo();
		msgVo.setTemplate_id(SysConfig.getPropertiesValue("template_id"));
		msgVo.setUrl(url);
		HashMap<String, HashMap<String, String>> topMap = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> firstMap = new HashMap<String, String>();
		firstMap.put("value", title);
		firstMap.put("color", "#173177");
		HashMap<String, String> yycontent = new HashMap<String, String>();
		yycontent.put("value", yycontent_str);
		yycontent.put("color", "#173177");
		HashMap<String, String> yytime = new HashMap<String, String>();
		yytime.put("value", yytime_str);
		yytime.put("color", "#173177");
		HashMap<String, String> remarkMap = new HashMap<String, String>();
		remarkMap.put("value", remark);
		remarkMap.put("color", "");
		topMap.put("first", firstMap);
		topMap.put("keyword1", yycontent);
		topMap.put("keyword2", yytime);
		topMap.put("remark", remarkMap);
		msgVo.setData(topMap);
		msgVo.setTouser(openid);
		sendMessage(msgVo);
	}
	
	/**
	 * 客服通知提醒
	 * @param title
	 * @param openid
	 * @param url
	 * @param yycontent_str
	 * @param yytime_str
	 * @param remark
	 */
	public static void doSendMsgToObjKF(String title,String openid, String url,String k1_str,String k2_str,String k3_str,String k4_str,String remark){
		MessageVo msgVo = new MessageVo();
		msgVo.setTemplate_id(SysConfig.getPropertiesValue("template_id_kfxx"));
		msgVo.setUrl(url);
		HashMap<String, HashMap<String, String>> topMap = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> firstMap = new HashMap<String, String>();
		firstMap.put("value", title);
		firstMap.put("color", "#173177");
		HashMap<String, String> k1 = new HashMap<String, String>();
		k1.put("value", k1_str);
		k1.put("color", "#173177");
		HashMap<String, String> k2 = new HashMap<String, String>();
		k2.put("value", k2_str);
		k2.put("color", "#173177");
		HashMap<String, String> k3 = new HashMap<String, String>();
		k3.put("value", k3_str);
		k3.put("color", "#173177");
		HashMap<String, String> k4 = new HashMap<String, String>();
		k4.put("value", k4_str);
		k4.put("color", "#173177");
		HashMap<String, String> remarkMap = new HashMap<String, String>();
		remarkMap.put("value", remark);
		remarkMap.put("color", "");
		topMap.put("first", firstMap);
		topMap.put("keyword1", k1);
		topMap.put("keyword2", k2);
		topMap.put("keyword3", k3);
		topMap.put("keyword4", k4);
		topMap.put("remark", remarkMap);
		msgVo.setData(topMap);
		msgVo.setTouser(openid);
		sendMessage(msgVo);
	}
	
	public static int sendMessage(MessageVo msgVo) {
		Memclient mc = Memclient.getInstance();
		int result = 0;
		String url = SENDMESSAGE_URL.replace("ACCESS_TOKEN", (String)mc.get("access_token"));
		System.out.println("url:" + url);
		String jsonMenu = JSONObject.parseObject(String.valueOf(msgVo)).toString();
		System.out.println("jsonMenu:" + jsonMenu);
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
		System.out.println("jsonObject:"+jsonObject);
		System.out.println(result);
		if (null != jsonObject) {
			if (0 != jsonObject.getInteger("errcode")) {
				result = jsonObject.getInteger("errcode");
			}
		}
		return result;
	}
	
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (ConnectException ce) {
			 
		} catch (Exception e) {
			 
		}
		return jsonObject;
	} 
	
	
	/**
	 * 用户成功支付推送
	 * @param accessToken
	 * @param first
	 * @param orderSn
	 * @param orderStatus
	 * @param remark
	 * @param openid
	 * @param url
	 */
	public static void doPayMsg(String accessToken, String title,String openid, String url,String keyword1,String keyword2,String keyword3,String keyword4,String remark){
		MessageVo msgVo = new MessageVo();
		msgVo.setTemplate_id(SysConfig.getPropertiesValue("template_zf_id"));
		msgVo.setUrl(url);
		HashMap<String, HashMap<String, String>> topMap = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> firstMap = new HashMap<String, String>();
		firstMap.put("value", title);
		firstMap.put("color", "#173177");
		HashMap<String, String> keyword1Map = new HashMap<String, String>();
		keyword1Map.put("value", keyword1);
		keyword1Map.put("color", "#173177");
		HashMap<String, String> keyword2Map = new HashMap<String, String>();
		keyword2Map.put("value", keyword2);
		keyword2Map.put("color", "#173177");
		HashMap<String, String> keyword3Map = new HashMap<String, String>();
		keyword3Map.put("value", keyword3);
		keyword3Map.put("color", "#173177");
		HashMap<String, String> keyword4Map = new HashMap<String, String>();
		keyword4Map.put("value", keyword4);
		keyword4Map.put("color", "#173177");
		HashMap<String, String> remarkMap = new HashMap<String, String>();
		remarkMap.put("value", remark);
		remarkMap.put("color", "");
		topMap.put("first", firstMap);
		topMap.put("keyword1", keyword1Map);
		topMap.put("keyword2", keyword2Map);
		topMap.put("keyword3", keyword3Map);
		topMap.put("keyword4", keyword4Map);
		topMap.put("remark", remarkMap);
		msgVo.setData(topMap);
		msgVo.setTouser(openid);
		sendMessage(msgVo);
	}
	
	
	
}
