package com.zhsz.dao.entity.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Type implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8234526702432367215L;

	private Type(Integer id, String value) {
		this.id = id;
		this.value = value;
	}

	private Integer id;

	private String value;

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public static final Type returns = new Type(-2, "退款");

	public static final Type revocation = new Type(-1, "撤销");

	public static final Type online_pay = new Type(1, "消费");

	public static String getFlowValue(Integer id) {
		switch (id) {
		case -2:
			return returns.getValue();
		case -1:
			return revocation.getValue();
		case 1:
			return online_pay.getValue();
		default:
			return "";
		}

	}
	public static List<Type> getAllType(){
		List<Type> list = new ArrayList<Type>();
		list.add(revocation);
		list.add(returns);
		list.add(online_pay);
		return list;
	}

}
