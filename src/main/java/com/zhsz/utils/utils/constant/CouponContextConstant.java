package com.zhsz.utils.utils.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 优惠券常量类
 * @author xindong
 *
 */
public class CouponContextConstant {
	/**
	 * 优惠券-e居洁系统号
	 */
	public static String COUPON_SYS_YJJ = "101";
	/**
	 * 状态-停用
	 */
	public static final Integer COUPON_STATUS_OFF = 0;
	/**
	 * 状态-启用
	 */
	public static final Integer COUPON_STATUS_ON = 1;
	/**
	 * 使用方式-全部
	 */
	public static final Integer COUPON_PAYMETHOD_ALL = 100;
	/**
	 * 使用方式-仅现金支付
	 */
	public static final Integer COUPON_PAYMETHOD_CASH = 101;
	/**
	 * 使用方式-仅微信支付
	 */
	public static final Integer COUPON_PAYMETHOD_WX = 200;
	/**
	 * 使用方式-仅微信公众号支付
	 */
	public static final Integer COUPON_PAYMETHOD_WX_PUBLIC = 201;
	/**
	 * 使用方式-仅微信app支付
	 */
	public static final Integer COUPON_PAYMETHOD_WX_APP = 202;
	/**
	 * 使用方式-仅微信扫码支付
	 */
	public static final Integer COUPON_PAYMETHOD_WX_SCAN = 203;
	/**
	 * 使用方式-仅支付宝支付
	 */
	public static final Integer COUPON_PAYMETHOD_WX_ALIPAY = 300;
	/**
	 * 生成渠道-管理后台
	 */
	public static final String COUPON_CHANNEL_MANAGER = "001";
	/**
	 * 使用状态-领取未使用
	 */
	public static final Integer COUPON_USER_STATUS_UNUSE = 10;
	/**
	 * 使用状态-已使用
	 */
	public static final Integer COUPON_USER_STATUS_USED = 11;
	/**
	 * 使用状态-已过期
	 */
	public static final Integer COUPON_USER_STATUS_OVERTIME = 12;
	/**
	 * 使用状态-已作废
	 */
	public static final Integer COUPON_USER_STATUS_STOP = 13;
	/**
	 * 返回码&返回信息集合
	 */
	public static Map<Integer, String> CONSTANT_MAP = null;
	
	/**
	 * 请求处理成功
	 */
	public static final Integer COUPON_CODE_SUCCEED = 777000;
	/**
	 * 请求处理失败
	 */
	public static final Integer COUPON_CODE_FAILED = 777001;
	/**
	 * 请求过程中产生未知异常
	 */
	public static final Integer COUPON_CODE_ERROR = 777999;
	/**
	 * 兑换码无效
	 */
	public static final Integer COUPON_CODE_CPCODE_INVALID = 777002;
	/**
	 * 兑换活动已结束
	 */
	public static final Integer COUPON_CODE_STOP = 777003;
	/**
	 * 兑换人数已满
	 */
	public static final Integer COUPON_CODE_FULL = 777004;
	/**
	 * 存在兑换记录
	 */
	public static final Integer COUPON_CODE_EXIST = 777005;
	/**
	 * 请求参数为空
	 */
	public static final Integer COUPON_CODE_NOPARAM = 777006;
	
	
	/**
	 * 根据返回码获取返回信息
	 * @param rtnCode
	 * @return rtnMsg
	 */
	public static String getMsgByCode(Integer rtnCode){
		if(null == CONSTANT_MAP){
			CONSTANT_MAP = new HashMap<Integer, String>();
			CONSTANT_MAP.put(777000, "请求处理成功");
			CONSTANT_MAP.put(777001, "请求处理失败");
			CONSTANT_MAP.put(777002, "兑换码无效");
			CONSTANT_MAP.put(777003, "兑换活动已结束");
			CONSTANT_MAP.put(777004, "兑换人数已满");
			CONSTANT_MAP.put(777005, "存在兑换记录");
			CONSTANT_MAP.put(777006, "请求参数为空");
			CONSTANT_MAP.put(777999, "请求过程中产生未知异常");
		}
		return CONSTANT_MAP.get(rtnCode);
	}
	
	/**
	 * 获取优惠券兑换码
	 * @param length
	 * @return String
	 */
	public static String getCouponCode(int length){
		String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    Random random=new Random();
	    StringBuffer sb=new StringBuffer();
	    for(int i=0;i<length;i++){
	      int number=random.nextInt(36);
	      sb.append(str.charAt(number));
	    }
	    return sb.toString();
	}
}
