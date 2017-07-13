package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.Weixin;
import com.zhsz.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * weixin数据库表名称
 * Weixin业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-06-08 10:05:47 
 */  
public interface WeixinService{
    int doInsert(Weixin t) throws ServiceException;
    int doUpdate(Weixin t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<Weixin> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<Weixin> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
    
    int doInsertWeixin(String json, String url) throws ServiceException;
}
