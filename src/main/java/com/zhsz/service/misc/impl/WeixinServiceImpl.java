package com.zhsz.service.misc.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhsz.dao.entity.misc.Weixin;
import com.zhsz.dao.mapper.misc.WeixinMapper;
import com.zhsz.service.misc.WeixinService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.utils.JsonUtils;

import com.zhsz.utils.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * weixin数据库表名称
 * Weixin业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-06-08 10:05:47 
 */  
@Service
public class WeixinServiceImpl implements WeixinService {
    // 引用DAO层
    @Autowired
    private WeixinMapper weixinMapper;
	
    @Override
	public int doInsert(Weixin t) throws ServiceException {
		try {
			return weixinMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, WeixinServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(Weixin t) throws ServiceException {
		try {
			return weixinMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, WeixinServiceImpl.class);
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
	public List<Weixin> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Weixin> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doInsertWeixin(String json, String url) throws ServiceException {
		try {
			Timestamp nousedate = new Timestamp(new Date().getTime());
			JSONObject obj = JSONObject.parseObject(json);
			String appmsgstat = obj.get("appmsgstat").toString();
			Map<String, Object> map_appmsgstat = JsonUtils.json2map(appmsgstat);
			String read_num= (String)map_appmsgstat.get("read_num").toString();
			String like_num= (String)map_appmsgstat.get("like_num").toString();
			
			Weixin weixin = new Weixin();
			weixin.setId(UUID.randomUUID().toString());
			weixin.setCreatetime(nousedate);
			weixin.setReadnum(read_num);
			weixin.setLikenum(like_num);
			//获取url中的sn
			if(StringUtils.isNotEmpty(url)){
				int startindex  = url.indexOf("&sn=");
				int endIndex = url.indexOf("&",url.indexOf("&sn=")+4);
				weixin.setSn(url.substring(startindex+4, endIndex));
			}
			int n = weixinMapper.doInsert(weixin);
			return n;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
}
