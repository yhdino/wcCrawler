package com.zhsz.utils.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.zhsz.utils.logger.ZhszLogger;

/** HTTP Client 工具类 */
public class HttpClientUtil {

	private static PoolingClientConnectionManager cm = null;

	static {
		try {
			cm = new PoolingClientConnectionManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 默认的HTTP响应实体编码 = "UTF-8" */
	private static final String DEFAULT_CHARSET = "UTF-8";

	// /////////////////////////////////////////////////////////////////////////
	// <<Get>>

	/** HTTP Get
	 * <p/>
	 * 响应内容实体采用<code>UTF-8</code>字符集
	 * 
	 * @param url
	 *        请求url
	 * @return 响应内容实体 */
	public static String get(String url) {
		return get(url, DEFAULT_CHARSET);
	}

	/** HTTP Get
	 * 
	 * @param url
	 *        请求url
	 * @param responseCharset
	 *        响应内容字符集
	 * @return 响应内容实体 */
	public static String get(String url, String responseCharset) {
		ZhszLogger.debug("Get [" + url + "] ...", HttpClientUtil.class);
		HttpClient client = new DefaultHttpClient(cm);
		try {
			HttpGet getMethod = new HttpGet(url);
			HttpResponse response = client.execute(getMethod);
			return consumeResponseEntity(response, responseCharset);
		} catch (Exception e) {
			ZhszLogger.error(e.getMessage(), HttpClientUtil.class);
		}
		return null;
	}

	// /////////////////////////////////////////////////////////////////////////
	// <<Post>>

	/** HTTP Post
	 * 
	 * @param url
	 *        请求url
	 * @param params
	 *        请求参数
	 * @return 响应内容实体 */
	public static String post(String url, Map<String, String> params) {
		return post(url, params, DEFAULT_CHARSET, DEFAULT_CHARSET);
	}

	/** HTTP Post
	 * 
	 * @param url
	 *        请求URL
	 * @param params
	 *        请求参数
	 * @param paramEncoding
	 *        请求参数编码
	 * @param responseCharset
	 *        响应内容字符集
	 * @return 响应内容实体 */
	public static String post(String url, Map<String, String> params,
			String paramEncoding, String responseCharset) {
		ZhszLogger.debug("Post [" + url + "] ...", HttpClientUtil.class);
		HttpClient client = new DefaultHttpClient(cm);
		try {
			HttpPost post = new HttpPost(url);
			if (params != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : params.keySet()) {
					paramList.add(new BasicNameValuePair(key, params.get(key)));
				}
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
						paramList, paramEncoding);
				post.setEntity(formEntity);
			}

			HttpResponse response = client.execute(post);
			return consumeResponseEntity(response, responseCharset);
		} catch (Exception e) {
			ZhszLogger.error(e.getMessage(), HttpClientUtil.class);
		}
		return null;
	}

	/** HTTP Post XML（使用默认字符集）
	 * 
	 * @param url
	 *        请求的URL
	 * @param xml
	 *        XML格式请求内容
	 * @return 响应内容实体 */
	public static String postXml(String url, String xml) {
		return postXml(url, xml, DEFAULT_CHARSET, DEFAULT_CHARSET);
	}

	/** HTTP Post XML
	 * 
	 * @param url
	 *        请求的URL
	 * @param xml
	 *        XML格式请求内容
	 * @param requestCharset
	 *        请求内容字符集
	 * @param responseCharset
	 *        响应内容字符集
	 * @return 响应内容实体 */
	public static String postXml(String url, String xml, String requestCharset,
			String responseCharset) {
		return post(url, xml, "text/xml; charset=" + requestCharset,
				"text/xml", requestCharset, responseCharset);
	}

	/** HTTP Post JSON（使用默认字符集）
	 * 
	 * @param url
	 *        请求的URL
	 * @param json
	 *        JSON格式请求内容
	 * @return 响应内容实体 */
	public static String postJson(String url, String json) {
		return postJson(url, json, DEFAULT_CHARSET, DEFAULT_CHARSET);
	}

	/** HTTP Post JSON
	 * 
	 * @param url
	 *        请求的URL
	 * @param json
	 *        JSON格式请求内容
	 * @param requestCharset
	 *        请求内容字符集
	 * @param responseCharset
	 *        响应内容字符集
	 * @return 响应内容实体 */
	public static String postJson(String url, String json,
			String requestCharset, String responseCharset) {
		return post(url, json, "application/json; charset=" + requestCharset,
				"application/json", requestCharset, responseCharset);
	}

	/** HTTP Post
	 * 
	 * @param url
	 *        请求的URL
	 * @param content
	 *        请求内容
	 * @param contentType
	 *        请求内容类型，HTTP Header中的<code>Content-type</code>
	 * @param mimeType
	 *        请求内容MIME类型
	 * @param requestCharset
	 *        请求内容字符集
	 * @param responseCharset
	 *        响应内容字符集
	 * @return 响应内容实体 */
	public static String post(String url, String content, String contentType,
			String mimeType, String requestCharset, String responseCharset) {
		ZhszLogger.debug("Post [" + url + "] ...", HttpClientUtil.class);
		HttpClient client = new DefaultHttpClient(cm);
		try {
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-Type", contentType);
			ContentType type = ContentType.create(mimeType, requestCharset);
			HttpEntity requestEntity = new StringEntity(content, type);
			post.setEntity(requestEntity);

			HttpResponse response = client.execute(post);
			return consumeResponseEntity(response, responseCharset);
		} catch (Exception e) {
			ZhszLogger.error(e.getMessage(), HttpClientUtil.class);
		}
		return null;
	}

	// /////////////////////////////////////////////////////////////////////////
	// <<SSL Get>>

	public static String sslGet(String url, Map<String, String> params,
			String keyType, String keyPath, String keyPassword, int sslport,
			String authString) {
		try {
			Scheme sch = getSslScheme(keyType, keyPath, keyPassword, sslport);
			return sslGet(url, params, sch, authString);
		} catch (Exception e) {
			ZhszLogger.error(e.getMessage(), HttpClientUtil.class);
		}

		return null;
	}

	public static String sslGet(String url, Map<String, String> params,
			Scheme sch, String authString) {
		ZhszLogger.debug("SSL Get [" + url + "] ...", HttpClientUtil.class);
		HttpClient client = new DefaultHttpClient(cm);
		try {
			client.getConnectionManager().getSchemeRegistry().register(sch);
			HttpGet getMethod = new HttpGet(url);

			if (authString != null)
				getMethod.setHeader("Authorization", authString);

			if (params != null) {
				HttpParams httpParams = new BasicHttpParams();
				for (String key : params.keySet()) {
					httpParams.setParameter(key, params.get(key));
				}
				getMethod.setParams(httpParams);
			}

			HttpResponse response = client.execute(getMethod);
			return consumeResponseEntity(response, DEFAULT_CHARSET);
		} catch (Exception e) {
			ZhszLogger.error(e.getMessage(), HttpClientUtil.class);
		}

		return null;
	}

	// /////////////////////////////////////////////////////////////////////////
	// <<SSL Post>>

	/** SSL Post XML（使用默认字符集）
	 * 
	 * @param url
	 *        请求的URL
	 * @param xml
	 *        XML格式请求内容
	 * @param keyType
	 *        密钥类型
	 * @param keyPath
	 *        密钥文件路径
	 * @param keyPassword
	 *        密钥文件密码
	 * @param sslPort
	 *        SSL端口
	 * @param authString
	 *        头部认证信息
	 * @return 响应内容实体 */
	public static String sslPostXml(String url, String xml, String keyType,
			String keyPath, String keyPassword, int sslPort, String authString) {
		try {
			Scheme sch = getSslScheme(keyType, keyPath, keyPassword, sslPort);
			return sslPost(url, xml, "text/xml; charset=" + DEFAULT_CHARSET,
					"text/xml", DEFAULT_CHARSET, DEFAULT_CHARSET, sch,
					authString);
		} catch (Exception e) {
			ZhszLogger.error(e.getMessage(), HttpClientUtil.class);
		}
		return null;
	}

	/** SSL Post JSON（使用默认字符集）
	 * 
	 * @param url
	 *        请求的URL
	 * @param json
	 *        JSON格式请求内容
	 * @param keyType
	 *        密钥类型
	 * @param keyPath
	 *        密钥文件路径
	 * @param keyPassword
	 *        密钥文件密码
	 * @param sslPort
	 *        SSL端口
	 * @param authString
	 *        头部认证信息
	 * @return 响应内容实体 */
	public static String sslPostJson(String url, String json, String keyType,
			String keyPath, String keyPassword, int sslPort, String authString) {
		try {
			Scheme sch = getSslScheme(keyType, keyPath, keyPassword, sslPort);
			return sslPost(url, json, "application/json; charset="
					+ DEFAULT_CHARSET, "application/json", DEFAULT_CHARSET,
					DEFAULT_CHARSET, sch, authString);
		} catch (Exception e) {
			ZhszLogger.error(e.getMessage(), HttpClientUtil.class);
		}
		return null;
	}

	/** SSL Post
	 * 
	 * @param url
	 *        请求的URL
	 * @param content
	 *        请求内容
	 * @param contentType
	 *        请求内容类型，HTTP Header中的<code>Content-type</code>
	 * @param mimeType
	 *        请求内容MIME类型
	 * @param requestCharset
	 *        请求内容字符集
	 * @param responseCharset
	 *        响应内容字符集
	 * @param sch
	 *        Scheme
	 * @param authString
	 *        头部信息中的<code>Authorization</code>
	 * @return 响应内容实体 */
	public static String sslPost(String url, String content,
			String contentType, String mimeType, String requestCharset,
			String responseCharset, Scheme sch, String authString) {
		ZhszLogger.debug("SSL Get [" + url + "] ...", HttpClientUtil.class);
		HttpClient client = new DefaultHttpClient(cm);
		try {
			client.getConnectionManager().getSchemeRegistry().register(sch);
			HttpPost post = new HttpPost(url);

			if (authString != null)
				post.setHeader("Authorization", authString);
			if (contentType != null)
				post.setHeader("Content-Type", contentType);
			ContentType type = ContentType.create(mimeType, requestCharset);
			HttpEntity requestEntity = new StringEntity(content, type);
			post.setEntity(requestEntity);

			HttpResponse response = client.execute(post);
			return consumeResponseEntity(response, responseCharset);
		} catch (Exception e) {
			ZhszLogger.error(e.getMessage(), HttpClientUtil.class);
		}
		return null;
	}

	// /////////////////////////////////////////////////////////////////////////
	// <<内部辅助方法>>

	/** 安全的消耗（获取）响应内容实体
	 * <p/>
	 * 使用 {@link EntityUtils} 将响应内容实体转换为字符串，同时关闭输入流
	 * <p/>
	 * //TODO 响应内容太长不适宜使用 EntityUtils
	 * 
	 * @param response
	 *        HttpResponse
	 * @param responseCharset
	 *        响应内容字符集
	 * @return 响应内容实体
	 * @throws IOException
	 *         IOException */
	private static String consumeResponseEntity(HttpResponse response,
			String responseCharset) throws IOException {
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			ZhszLogger.debug(
					"Response status line: " + response.getStatusLine(),
					HttpClientUtil.class);
			HttpEntity responseEntity = response.getEntity();
			String responseBody = EntityUtils.toString(responseEntity,
					responseCharset);
			ZhszLogger.debug("Response body: \n" + responseBody,
					HttpClientUtil.class);
			return responseBody;
		} else {
			ZhszLogger.warn(
					"Response status line: " + response.getStatusLine(),
					HttpClientUtil.class);
		}
		return null;
	}

	private static Scheme getSslScheme(String keyType, String keyPath,
			String keyPassword, int sslPort) throws Exception {
		KeyStore trustStore = KeyStore.getInstance(keyType);
		FileInputStream instream = new FileInputStream(new File(keyPath));
		try {
			trustStore.load(instream, keyPassword.toCharArray());
		} finally {
			try {
				instream.close();
			} catch (Exception e) {
				ZhszLogger.error(e.getMessage(), HttpClientUtil.class);
			}
		}
		// 验证密钥源
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");
		kmf.init(trustStore, keyPassword.toCharArray());

		// 同位体验证信任决策源
		TrustManager[] trustManagers = {new MyX509TrustManager()};

		// 初始化安全套接字
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(kmf.getKeyManagers(), trustManagers, null);
		SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext);
		return new Scheme("https", sslPort, socketFactory);
	}

}
