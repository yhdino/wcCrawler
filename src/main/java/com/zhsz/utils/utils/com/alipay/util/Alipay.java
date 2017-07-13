package com.zhsz.utils.utils.com.alipay.util;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhsz.utils.utils.com.alipay.config.AlipayConfig;
import com.zhsz.utils.utils.com.alipay.sign.RSA;

public class Alipay {
	public void doAlipay(HttpServletRequest request,
			HttpServletResponse response, Map<String, String> payinfo) {
		response.setCharacterEncoding("utf-8");
		try {
			String json = GetPayInfo(payinfo);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("InfoMessage",
					"信息载入失败！具体异常信息：" + e.getMessage());
		}
	}

	public String GetPayInfo(Map<String, String> payinfo) {
		String orderInfo = createLinkString(payinfo);
		;
		String sign = RSA.sign(orderInfo, AlipayConfig.private_key,
				AlipayConfig.input_charset);
		try {
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
		System.out.println(payInfo);
		return payInfo;
	}

	public String getSignType() {
		return "sign_type=\"RSA\"";
	}

	public String createLinkString(Map map) {
		String str = "";
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			String value = (String) map.get(key);
			str = str + key + "=" + value + "&";
		}
		return str.substring(0, str.length() - 1);
	}
}