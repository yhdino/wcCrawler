package com.zhsz.service.misc.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhsz.dao.entity.misc.Post;
import com.zhsz.dao.mapper.misc.PostMapper;
import com.zhsz.service.misc.PostService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.utils.JsonUtils;
import com.zhsz.utils.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * post数据库表名称
 * Post业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-06-08 10:05:47 
 */  
@Service
public class PostServiceImpl implements PostService {
	
    // 引用DAO层
    @Autowired
    private PostMapper postMapper;
	
    @Override
	public int doInsert(Post t) throws ServiceException {
		try {
			return postMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(Post t) throws ServiceException {
		try {
			return postMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public long doQueryCountByCondition(Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Post> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		try {
			return postMapper.doQueryByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Post> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		try {
			return postMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doInsertTest1(String json,String biz,String param) throws ServiceException {
		
		try {
			int num = 0;
			Timestamp nousedate = new Timestamp(new Date().getTime());
			//json = "var msgList = '{&quot;list&quot;:[{&quot;comm_msg_info&quot;:{&quot;id&quot;:1000000039,&quot;type&quot;:49,&quot;datetime&quot;:1496744482,&quot;fakeid&quot;:&quot;3004014892&quot;,&quot;status&quot;:2,&quot;content&quot;:&quot;&quot;},&quot;app_msg_ext_info&quot;:{&quot;title&quot;:&quot;你只管放心去飞，我负责保驾护航&quot;,&quot;digest&quot;:&quot;厉害了我的达！&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950850,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434504&amp;amp;idx=1&amp;amp;sn=1f7a49c6857c78442df70981f49af717&amp;amp;chksm=80a3cdcbb7d444dd7939318a5a4016189c653182fe829b94cb6c7d8fba67b345bc595c3d8946&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;https:\\/\\/www.pailequ.cn\\/toc\\/corp\\/index_v2\\/activity\\/coupon?activityCode=gaokao&amp;amp;outerSrc=gaokao&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_png\\/3lpA1icr8avDJIyiae9aibXhE73bBSHuibCvtdlEuFgRDF3yCb5jSAHgNthCrQ3MeYuRkFPqmQ51GSyy0I2shK7rrA\\/0?wx_fmt=png&quot;,&quot;subtype&quot;:9,&quot;is_multi&quot;:1,&quot;multi_app_msg_item_list&quot;:[{&quot;title&quot;:&quot;免费电影&nbsp;|&nbsp;【治愈系】遇见最美好的意外&quot;,&quot;digest&quot;:&quot;你是否曾经多么希望自己眼前经历的这一切，原来都只是夏日午后趴在学校教室课桌上做的一场梦。&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950849,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434504&amp;amp;idx=2&amp;amp;sn=def43822a4bdb95617173d2ac6038657&amp;amp;chksm=80a3cdcbb7d444dd3b7a6d65e1e44e3f72d5e97f4bc591b1815d0f57830b61b92058e51a9468&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_png\\/3lpA1icr8avA7juHTf2T3UqHcbxRszoejuFNP38AxDybOA3neHqG5nFJQjNviaOhz3PPymxhLiamgyzXZ4HjiaMGLw\\/0?wx_fmt=png&quot;,&quot;author&quot;:&quot;最温暖的&quot;,&quot;copyright_stat&quot;:100}],&quot;author&quot;:&quot;最温暖的&quot;,&quot;copyright_stat&quot;:11}},{&quot;comm_msg_info&quot;:{&quot;id&quot;:1000000038,&quot;type&quot;:49,&quot;datetime&quot;:1496365268,&quot;fakeid&quot;:&quot;3004014892&quot;,&quot;status&quot;:2,&quot;content&quot;:&quot;&quot;},&quot;app_msg_ext_info&quot;:{&quot;title&quot;:&quot;福利&nbsp;|&nbsp;看美女辣妈于明加直播，互动赢大礼！&quot;,&quot;digest&quot;:&quot;就在今天下午3点，千万不要错过！&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950838,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434490&amp;amp;idx=1&amp;amp;sn=0b388fad2aba0552f4f9b89874126eb9&amp;amp;chksm=80a3cd39b7d4442f24be3b38207a8bc6fe97916c3d3665ddbece647d889d999be7efeb84f988&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;https:\\/\\/www.pailequ.cn\\/toc\\/corp\\/index_v2\\/activity\\/coupon?activityCode=common&amp;amp;outerSrc=common&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/3lpA1icr8avC7y5qapHSYiaG9s6u3coWhLKKq7CGmQcfiaFS3ibGnCwFy7MSQzRkRiarA11lA0OnYHOUdLHXc9OFQeA\\/0?wx_fmt=jpeg&quot;,&quot;subtype&quot;:9,&quot;is_multi&quot;:0,&quot;multi_app_msg_item_list&quot;:[],&quot;author&quot;:&quot;最靠谱的&quot;,&quot;copyright_stat&quot;:11}},{&quot;comm_msg_info&quot;:{&quot;id&quot;:1000000037,&quot;type&quot;:49,&quot;datetime&quot;:1495525132,&quot;fakeid&quot;:&quot;3004014892&quot;,&quot;status&quot;:2,&quot;content&quot;:&quot;&quot;},&quot;app_msg_ext_info&quot;:{&quot;title&quot;:&quot;听说，达达变了&quot;,&quot;digest&quot;:&quot;不只是变了，还变得更好了。&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950806,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434479&amp;amp;idx=1&amp;amp;sn=2756777687185fe08aa910f6524eb201&amp;amp;chksm=80a3cd2cb7d4443aa00cbedcb54f592d50e63dab0475e31e76d3063954da60c70f25ab22e63e&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;https:\\/\\/www.imdada.cn\\/redirect?app=shangjia&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/3lpA1icr8avAPK8QSpw6ibEGhLWtUr0o9Pk0A0KdRibvwIpQGRbiazCGrqYU3sYBxv2go327U0ibIYGYLWB7bDsxmvA\\/0?wx_fmt=jpeg&quot;,&quot;subtype&quot;:9,&quot;is_multi&quot;:0,&quot;multi_app_msg_item_list&quot;:[],&quot;author&quot;:&quot;全新的&quot;,&quot;copyright_stat&quot;:11}},{&quot;comm_msg_info&quot;:{&quot;id&quot;:1000000036,&quot;type&quot;:49,&quot;datetime&quot;:1495240265,&quot;fakeid&quot;:&quot;3004014892&quot;,&quot;status&quot;:2,&quot;content&quot;:&quot;&quot;},&quot;app_msg_ext_info&quot;:{&quot;title&quot;:&quot;520达达让爱说到就到！还有双重福利送给你！&quot;,&quot;digest&quot;:&quot;520达达专属优惠券和京东到家5折新人券，送给有爱的你！&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950828,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434478&amp;amp;idx=1&amp;amp;sn=39c8e7b207d19750317f80c94a1bc28d&amp;amp;chksm=80a3cd2db7d4443bc2e301b7bfb587435903370ff9811d5a2c97950a4f5dde198cde485c758d&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/3lpA1icr8avCyYKtBKRxnG5Eqvtpfiaqc37cicly2l4sHbSszksnD1Y8xelQyakyGJjkv5RBBqyAZ1JiaFqnAvdxjA\\/0?wx_fmt=jpeg&quot;,&quot;subtype&quot;:9,&quot;is_multi&quot;:1,&quot;multi_app_msg_item_list&quot;:[{&quot;title&quot;:&quot;免费电影&nbsp;|&nbsp;治愈系情感喜剧《美容针》，达达请你看！&quot;,&quot;digest&quot;:&quot;当二次元爱恋撞进三次元，会发生什么？《美容针》为你揭晓！&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950813,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434478&amp;amp;idx=2&amp;amp;sn=d90663c70ad0f422079546281c3e878c&amp;amp;chksm=80a3cd2db7d4443ba33fc31ec0359f85cbf505c9545af6faf9979f52ef0947c94392026128f8&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/3lpA1icr8avAoNE1JqQKEB9DLv42phSC7Z5s1OA1h5S4eViaklRciagl3wicnhbaFG5qmfLeY3UFZjuWHrPiaKArrhg\\/0?wx_fmt=jpeg&quot;,&quot;author&quot;:&quot;最靠谱的&quot;,&quot;copyright_stat&quot;:11}],&quot;author&quot;:&quot;最靠谱的&quot;,&quot;copyright_stat&quot;:11}},{&quot;comm_msg_info&quot;:{&quot;id&quot;:1000000035,&quot;type&quot;:49,&quot;datetime&quot;:1494720276,&quot;fakeid&quot;:&quot;3004014892&quot;,&quot;status&quot;:2,&quot;content&quot;:&quot;&quot;},&quot;app_msg_ext_info&quot;:{&quot;title&quot;:&quot;20元券&nbsp;|&nbsp;用达达，母亲节让爱不迟到&quot;,&quot;digest&quot;:&quot;即便无法陪伴在妈妈身旁，你的爱与心意，还有达达为你准时送到。&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950794,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434450&amp;amp;idx=1&amp;amp;sn=ac658604340cdc05602d344998b63174&amp;amp;chksm=80a3cd11b7d4440703d669d63dcaa7a622cdd4d5f13fbab4ca1a7cc60de8b64596c03cf3f5c1&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;http:\\/\\/url.cn\\/491tegY&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_png\\/3lpA1icr8avAuiaGPicqnKWgo4JwkzYG5Kib1fqm32WxVXPibDPyfdKSCkgz4V6QQCZYphcY0yF1GyxozrxVCdDrmAQ\\/0?wx_fmt=png&quot;,&quot;subtype&quot;:9,&quot;is_multi&quot;:0,&quot;multi_app_msg_item_list&quot;:[],&quot;author&quot;:&quot;最温暖的&quot;,&quot;copyright_stat&quot;:11}},{&quot;comm_msg_info&quot;:{&quot;id&quot;:1000000034,&quot;type&quot;:49,&quot;datetime&quot;:1493866402,&quot;fakeid&quot;:&quot;3004014892&quot;,&quot;status&quot;:2,&quot;content&quot;:&quot;&quot;},&quot;app_msg_ext_info&quot;:{&quot;title&quot;:&quot;今天，我们想和你分享几个蓝色的故事&quot;,&quot;digest&quot;:&quot;看完别哭，答应我好吗？&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950788,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434438&amp;amp;idx=1&amp;amp;sn=180ecf00f7573df9689c009830c26aee&amp;amp;chksm=80a3cd05b7d44413f4876f6c7211c42f92f00a8de2ef52dc3287a49e1ae95c55adc2451df09e&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;http:\\/\\/v.qq.com\\/x\\/page\\/w0399a2f9kw.html&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/3lpA1icr8avDW8EDaHOLexfRicVccec1Y6LicfxsiatiajGKdicmXicrbzRYnd2h6HZPSkfPooUxyibCjLlq3ckVr4t3Bg\\/0?wx_fmt=jpeg&quot;,&quot;subtype&quot;:9,&quot;is_multi&quot;:0,&quot;multi_app_msg_item_list&quot;:[],&quot;author&quot;:&quot;最温暖的&quot;,&quot;copyright_stat&quot;:11}},{&quot;comm_msg_info&quot;:{&quot;id&quot;:1000000033,&quot;type&quot;:49,&quot;datetime&quot;:1492929757,&quot;fakeid&quot;:&quot;3004014892&quot;,&quot;status&quot;:2,&quot;content&quot;:&quot;&quot;},&quot;app_msg_ext_info&quot;:{&quot;title&quot;:&quot;【进行中】为山区孩子捐赠书籍，你只需现在下单，达达骑士免费上门收书&quot;,&quot;digest&quot;:&quot;即刻下单捐书，达达骑士将免费上门取书，奉献爱心，就现在。&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950771,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434424&amp;amp;idx=1&amp;amp;sn=f109313e60d1d0dfb01b088b17654bcd&amp;amp;chksm=80a3cd7bb7d4446d0b634a12bba223a4545bff856ab57900fafa1436731fbae1945bc0e4b648&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/3lpA1icr8avCiautMThV6snGcVSSwcUHYWhwcVpMibXmp5BGl1qXLPIqZibcGzzQOzzicB76qicWjhQuvxpXQWbyPv5g\\/0?wx_fmt=jpeg&quot;,&quot;subtype&quot;:9,&quot;is_multi&quot;:0,&quot;multi_app_msg_item_list&quot;:[],&quot;author&quot;:&quot;&quot;,&quot;copyright_stat&quot;:100}},{&quot;comm_msg_info&quot;:{&quot;id&quot;:1000000032,&quot;type&quot;:49,&quot;datetime&quot;:1492829835,&quot;fakeid&quot;:&quot;3004014892&quot;,&quot;status&quot;:2,&quot;content&quot;:&quot;&quot;},&quot;app_msg_ext_info&quot;:{&quot;title&quot;:&quot;为山区孩子捐赠书籍，你的爱心准备好了吗？达达快送免费上门收书&quot;,&quot;digest&quot;:&quot;这一次，达达快送，为爱出发。&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950771,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434421&amp;amp;idx=1&amp;amp;sn=20d629997a0aa322dc263b423564622a&amp;amp;chksm=80a3cd76b7d4446033675ee7a9618b421445fa55f38d3c367d4562e571cfa4f9a51ef5cf86b4&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;http:\\/\\/url.cn\\/47g9mA8&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/3lpA1icr8avCiautMThV6snGcVSSwcUHYWhwcVpMibXmp5BGl1qXLPIqZibcGzzQOzzicB76qicWjhQuvxpXQWbyPv5g\\/0?wx_fmt=jpeg&quot;,&quot;subtype&quot;:9,&quot;is_multi&quot;:0,&quot;multi_app_msg_item_list&quot;:[],&quot;author&quot;:&quot;最温暖的&quot;,&quot;copyright_stat&quot;:11}},{&quot;comm_msg_info&quot;:{&quot;id&quot;:1000000031,&quot;type&quot;:49,&quot;datetime&quot;:1492140765,&quot;fakeid&quot;:&quot;3004014892&quot;,&quot;status&quot;:2,&quot;content&quot;:&quot;&quot;},&quot;app_msg_ext_info&quot;:{&quot;title&quot;:&quot;415周年庆&nbsp;|&nbsp;下单送券，福利满满&quot;,&quot;digest&quot;:&quot;达达-京东到家415周年庆，发福利啦！&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950763,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434415&amp;amp;idx=1&amp;amp;sn=644e623ce9356a18a00ce9c5e456ce68&amp;amp;chksm=80a3cd6cb7d4447a46b872151273b52e3fad0bf9e935059cc483ee24bb735ff5b21786cbdf6e&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;http:\\/\\/url.cn\\/47OMopO&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/3lpA1icr8avBvVr05uacqCAyE8VQsoUune3r7ytk5J2FhxLCgZJoNl1vnMWJhaL1u5onJeWOZ0kpVyghVd9sdAw\\/0?wx_fmt=jpeg&quot;,&quot;subtype&quot;:9,&quot;is_multi&quot;:1,&quot;multi_app_msg_item_list&quot;:[{&quot;title&quot;:&quot;20元券&nbsp;|&nbsp;收下他，单身狗一样可以撒狗粮&quot;,&quot;digest&quot;:&quot;黑色情人节，狗粮任你洒！&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950757,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434415&amp;amp;idx=2&amp;amp;sn=9a5c041c5b2f929fd24dbf39ea3abed1&amp;amp;chksm=80a3cd6cb7d4447a35de3249865b104f1ce585590489ae4387a22958f3102976e8949c799b32&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;http:\\/\\/url.cn\\/47OOtQW&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_png\\/3lpA1icr8avBvVr05uacqCAyE8VQsoUundqKG3FOgmSwRSat6cNGXVKSFib0X8NwG9XLscIKlUZxN1XGP5p1PuUQ\\/0?wx_fmt=png&quot;,&quot;author&quot;:&quot;最温暖的&quot;,&quot;copyright_stat&quot;:11}],&quot;author&quot;:&quot;最温暖的&quot;,&quot;copyright_stat&quot;:100}},{&quot;comm_msg_info&quot;:{&quot;id&quot;:1000000030,&quot;type&quot;:49,&quot;datetime&quot;:1491800822,&quot;fakeid&quot;:&quot;3004014892&quot;,&quot;status&quot;:2,&quot;content&quot;:&quot;&quot;},&quot;app_msg_ext_info&quot;:{&quot;title&quot;:&quot;厉害了，我的达达快送（内含优惠体验券）&quot;,&quot;digest&quot;:&quot;达达携手德高巴士和易起读，开启&amp;quot;移动捐书库&amp;quot;公益活动。&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950752,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434404&amp;amp;idx=1&amp;amp;sn=a0bee6b68652dfbfadf1857004f567c8&amp;amp;chksm=80a3cd67b7d444716903b89f912a88ab905efedd0dfa201156d5fa7fcd7d31536ede5063af0a&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;http:\\/\\/url.cn\\/47Bj6U0&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/3lpA1icr8avC0dHN84chDTibzdcWejYXsL6GkGPicN30eMKOpV5bibvv6U5iahrQibx6NMtpwr36eh4Ybwyj1bXVSvFg\\/0?wx_fmt=jpeg&quot;,&quot;subtype&quot;:9,&quot;is_multi&quot;:1,&quot;multi_app_msg_item_list&quot;:[{&quot;title&quot;:&quot;20元券&nbsp;|&nbsp;陪伴是最长情的告白&quot;,&quot;digest&quot;:&quot;比起病魔，他们更害怕孤单&quot;,&quot;content&quot;:&quot;&quot;,&quot;fileid&quot;:509950747,&quot;content_url&quot;:&quot;http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAwNDAxNDg5Mg==&amp;amp;mid=2657434404&amp;amp;idx=2&amp;amp;sn=60e2b24572ad267fff314c423a098bb3&amp;amp;chksm=80a3cd67b7d44471d54d2a8742a9509e20fff9a3ad0eb21bc58a5e52252d603e95bcad185552&amp;amp;scene=27#wechat_redirect&quot;,&quot;source_url&quot;:&quot;http:\\/\\/url.cn\\/47Bj5de&quot;,&quot;cover&quot;:&quot;http:\\/\\/mmbiz.qpic.cn\\/mmbiz_png\\/3lpA1icr8avAvLsDy4kTPbh0CvGa3nbcF6SBdIf2zJM7Es60gfskRsuyoMmWyicRRv8prfJzsDoo968OL8lDKtZw\\/0?wx_fmt=png&quot;,&quot;author&quot;:&quot;最温暖的&quot;,&quot;copyright_stat&quot;:11}],&quot;author&quot;:&quot;最温暖的&quot;,&quot;copyright_stat&quot;:11}}]}";
			String jsons =json.substring(json.indexOf("{")).replace("&quot;", "\"").replace("&nbsp"," ").replace("&amp;amp;", "&");

			JSONObject obj = JSONObject.parseObject(jsons);
			JSONArray array = (JSONArray) JSONArray.toJSON(obj.get("list"));
			
			if(array.size()!=0){
				
				Map<String, Object> condition_judge= new HashMap<String, Object>();
				
				for(int i=0;i<array.size();i++){
					String comm_msg_info = array.getJSONObject(i).get("comm_msg_info").toString();
					Map<String, Object> map_comm_msg_info = JsonUtils.json2map(comm_msg_info);
					String content_id= (String)map_comm_msg_info.get("id").toString();
					String type = (String)map_comm_msg_info.get("type").toString();
					String datetime =(String)map_comm_msg_info.get("datetime").toString();
					
					String app_msg_ext_info = array.getJSONObject(i).get("app_msg_ext_info").toString();
					Map<String, Object> map_app_msg_ext_info = JsonUtils.json2map(app_msg_ext_info);
					String title= (String)map_app_msg_ext_info.get("title").toString();
					String digest= (String)map_app_msg_ext_info.get("digest").toString();
					String fileid= (String)map_app_msg_ext_info.get("fileid").toString();
					String content_url= (String)map_app_msg_ext_info.get("content_url").toString();
					String source_url= (String)map_app_msg_ext_info.get("source_url").toString();
					String cover= (String)map_app_msg_ext_info.get("cover").toString();
					String is_multi= (String)map_app_msg_ext_info.get("is_multi").toString();
					Post post = new Post();
					post.setId(UUID.randomUUID().toString());
					post.setContent_id(content_id);
					post.setType(type);
					post.setDatetime(datetime);
					post.setTitle(title);
					post.setDigest(digest);
					post.setField_id(fileid);
					post.setContent_url(content_url.replace("\\", ""));
					//post.setContent_url("http://mp.weixin.qq.com/s?__biz=MzA3MTIwNTg4MA==&mid=2649829145&idx=1&sn=c7788abf00f370e9fdbb925f11353a7f&scene=27#wechat_redirect");
					//获取contenturl中的sn
					if(StringUtils.isNotEmpty(post.getContent_url())){
						int startindex  = post.getContent_url().indexOf("&sn=");
						int endIndex = post.getContent_url().indexOf("&",post.getContent_url().indexOf("&sn=")+4);
						post.setSn(post.getContent_url().substring(startindex+4, endIndex));
					}
					post.setSource_url(source_url.replace("\\", ""));
					post.setCover(cover);
					post.setIs_multi(is_multi);
					post.setCreatetime(nousedate);
					post.setBiz(biz);
					post.setBz1(param);
					//判断是否有重复的文章
					condition_judge.put("content_url", post.getContent_url());
					Long count = postMapper.doQueryCountByCondition(condition_judge);
					if(count==0){
						postMapper.doInsert(post);
						num++;
					}
					condition_judge.clear();
					JSONArray array_multi_app = (JSONArray) JSONArray.toJSON(map_app_msg_ext_info.get("multi_app_msg_item_list"));
					if(array_multi_app.size()>0){
						post.setId(UUID.randomUUID().toString());
						post.setTitle(array_multi_app.getJSONObject(0).get("title").toString());
						post.setDigest(array_multi_app.getJSONObject(0).get("digest").toString());
						post.setField_id(array_multi_app.getJSONObject(0).get("fileid").toString());
						post.setContent_url(array_multi_app.getJSONObject(0).get("content_url").toString().replace("\\", ""));
						if(StringUtils.isNotEmpty(post.getContent_url())){
							int startindex  = post.getContent_url().indexOf("&sn=");
							int endIndex = post.getContent_url().indexOf("&",post.getContent_url().indexOf("&sn=")+4);
							post.setSn(post.getContent_url().substring(startindex+4, endIndex));
						}
						post.setSource_url(array_multi_app.getJSONObject(0).get("source_url").toString().replace("\\", ""));
						post.setCover(array_multi_app.getJSONObject(0).get("cover").toString());
						condition_judge.put("content_url", post.getContent_url());
						Long count1 = postMapper.doQueryCountByCondition(condition_judge);
						if(count1 == 0){
							postMapper.doInsert(post);
							num++;
						}
						condition_judge.clear();
					}

				}
			}
			return num;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Post> doQueryByPostDetail(Map<String, Object> condition) throws ServiceException {
		try {
			return postMapper.doQueryByPostDetail(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostServiceImpl.class);
			throw new ServiceException(e);
		}
	}
}
