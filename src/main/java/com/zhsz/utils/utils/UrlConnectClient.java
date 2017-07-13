package com.zhsz.utils.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class UrlConnectClient {
	private static Logger log = LoggerFactory.getLogger(UrlConnectClient.class);

	/**
	 * https HttpsURLConnection
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			} else {
				httpUrlConn.connect();
			}
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
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
			log.error("server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	/**
	 * http URLConnection
	 */
	public static String httpRequest(String url) {
		String result = "";// 访问返回结果
		BufferedReader read = null;// 读取访问结果
		try {
			// 创建url
			URL realurl = new URL(url);
			// 打开连接
			URLConnection connection = realurl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立连接
			connection.connect();
			read = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String line;// 循环读取
			while ((line = read.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (read != null) {// 关闭流
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}