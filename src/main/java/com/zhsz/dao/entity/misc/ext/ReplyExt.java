package com.zhsz.dao.entity.misc.ext;

/**
 * Reply实体类的扩展类
 * @author 黄冬杰
 * @Date Date: 2017-06-19 15:43:12 
 */  
public class ReplyExt {
    // 关联属性
	private String positivenum;
	private String negativenum;
	private String neutralnum;
	// 前台参数
	public String getPositivenum() {
		return positivenum;
	}
	public void setPositivenum(String positivenum) {
		this.positivenum = positivenum;
	}
	public String getNegativenum() {
		return negativenum;
	}
	public void setNegativenum(String negativenum) {
		this.negativenum = negativenum;
	}
	public String getNeutralnum() {
		return neutralnum;
	}
	public void setNeutralnum(String neutralnum) {
		this.neutralnum = neutralnum;
	}
	
	
	
	
}
