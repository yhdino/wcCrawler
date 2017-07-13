package com.zhsz.utils.utils.yjj;

import java.util.HashMap;

/**
 * 微信推送类
 * @author Daland
 *
 */
public class MessageVo implements java.io.Serializable {

	private static final long serialVersionUID = -8812983986306747871L;
	private String touser;
	private String template_id;
	private String url;
	private String topcolor;
	private HashMap<String, HashMap<String, String>> data;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTopcolor() {
		return topcolor;
	}
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
	public HashMap<String, HashMap<String, String>> getData() {
		return data;
	}
	public void setData(HashMap<String, HashMap<String, String>> data) {
		this.data = data;
	}
}
