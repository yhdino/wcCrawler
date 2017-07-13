package com.zhsz.utils.utils.constant;

public class ZhbjContextConstant {
	
	public static final String OPER_UPDATE = "up";
	public static final String OPER_DELETE = "del";
	
	public static final Integer COMMON_STATUS_ON = 1;
	public static final Integer COMMON_STATUS_OFF = 0;
	public static final Integer COMMON_STATUS_PRE = 8;
	public static final Integer COMMON_STATUS_PRE_OVE = 9;
	
	public static final Integer ZHSQ_COMMON_STATUS_ON = 0;
	public static final Integer ZHSQ_COMMON_STATUS_OFF = 1;
	
	public static final String COMMODITY_STATUS_ON = "0";
	public static final String COMMODITY_STATUS_OFF = "1";
	
	public static final Integer AREA_USE = 0;
	public static final Integer AREA_UNUSE = 1;
	
	public static final String ISFULL_YES = "0";
	public static final String ISFULL_NO = "1";

	public static final Integer COMMODITY_TYPE = 2;
	public static final Integer ORDER_TYPE = 777;
	public static final String STORE_ID = "";
	
	/**
	 * 预约单状态
	 * 0：已取消
	 * 10：待派单
	 * 20：已派单-未接单，21：已派单-未出发，22:已派单-未到达
	 * 30：服务中
	 * 40：服务完成-已验收待支付，41：服务完成-支付失败，42：服务完成-支付成功(已完成) ，43：服务完成-已评价
	 */
	public static final Integer YYD_STATUS_CANCEL = 0;
	public static final Integer YYD_STATUS_WAIT = 10;
	public static final Integer YYD_STATUS_SEND_WAIT_ACCEPT = 20;
	public static final Integer YYD_STATUS_SEND_WAIT_COME = 21;
//	public static final Integer YYD_STATUS_SEND_WAIT_ARRIVE = 22;
	public static final Integer YYD_STATUS_SERVICE = 30;
	public static final Integer YYD_STATUS_FINISH_WAITPAY = 40;
	public static final Integer YYD_STATUS_FINISH_PAY_ERROR = 41;
	public static final Integer YYD_STATUS_FINISH_PAY_SUCCESS = 42;
	public static final Integer YYD_STATUS_FINISHED = 43;
	public static final Integer YYD_STATUS_UNNORMAL_CLOSED = 99;
	
	public static final String PAY_WAY_ALIPAY = "0";
	public static final String PAY_WAY_CASH = "1";
	public static final String PAY_WAY_WECHAT = "2";
	
	/**
	 * 订单状态
	 * 3：交易完成
	 */
	public static final Integer ORDER_STATUS_OVER = 3;
	
	/**
	 * 发货状态：
	 * 2：确认收货
	 */
	public static final Integer ORDER_DELIVER_STATUS_ENSURE = 2;
	
	/**
	 * 阿姨招募状态
	 * 0-待审核，1-审核通过，2-审核不通过
	 */
	public static final Integer AUNT_APPLY_WAIT = 0;
	public static final Integer AUNT_APPLY_PASS = 1;
	public static final Integer AUNT_APPLY_REFUSE = 2;
}
