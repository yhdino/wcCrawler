package com.zhsz.dao.entity.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class PayState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1969684271950903740L;

	private PayState(Integer id, String value) {
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

	public boolean isEqual(Integer state) {
		return getId().intValue() == state.intValue();
	}
	
	public static PayState refund_failure = new PayState(-7, "退款失败");

	public static PayState revocation_failure = new PayState(-6, "撤销失败");

	public static PayState waiting_refund = new PayState(-5, "等待退款");

	public static PayState waiting_revocation = new PayState(-4, "等待撤销");

	public static PayState refunded = new PayState(-3, "退款成功");

	public static PayState revocation = new PayState(-2, "撤销成功");

	public static PayState pay_failure = new PayState(-1, "付款失败");

	public static PayState not_pay = new PayState(0, "未付款");

	public static PayState waiting = new PayState(1, "等待确认");

	public static PayState pay_success = new PayState(2, "付款成功");

	public static String getStateValue(Integer id) {
		switch (id) {
			case -7 :
				return refund_failure.getValue();
			case -6 :
				return revocation_failure.getValue();
			case -5 :
				return waiting_refund.getValue();
			case -4 :
				return waiting_revocation.getValue();
			case -3 :
				return refunded.getValue();
			case -2 :
				return revocation.getValue();
			case -1 :
				return pay_failure.getValue();
			case 0 :
				return not_pay.getValue();
			case 1 :
				return waiting.getValue();
			case 2 :
				return pay_success.getValue();
			default :
				return "";
		}

	}

	public static List<PayState> getAllState() {
		List<PayState> list = new ArrayList<PayState>();
		list.add(not_pay);
		list.add(pay_failure);
		list.add(waiting);
		list.add(pay_success);
		list.add(revocation);
		list.add(refunded);
		list.add(waiting_revocation);
		list.add(waiting_refund);
		list.add(revocation_failure);
		list.add(refund_failure);
		return list;
	}
	
	public static void main(String[] args) {
		String aa="%E5%95%86%E6%88%B7%E4%B8%8D%E5%AD%98%E5%9C%A8";
		try {
			String asdfsd = URLDecoder.decode(aa, "UTF-8");
			System.out.println(asdfsd);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
