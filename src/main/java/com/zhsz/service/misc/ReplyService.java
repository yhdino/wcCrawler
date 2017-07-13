package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.Reply;
import com.zhsz.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * reply数据库表名称
 * Reply业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-06-19 15:43:12 
 */  

public interface ReplyService{
    int doInsert(Reply t) throws ServiceException;
    int doUpdate(Reply t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<Reply> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<Reply> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
    
    String doInsertReply(String json, String url) throws ServiceException;
    List<Reply> doQueryByReply(Map<String, Object> condition) throws ServiceException;
}
