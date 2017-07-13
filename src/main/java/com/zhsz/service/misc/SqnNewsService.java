package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.SqnNews;
import com.zhsz.utils.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * sqn_news数据库表名称
 * SqnNews业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-06-07 14:53:36 
 */  
public interface SqnNewsService{
    int doInsert(SqnNews t) throws ServiceException;
    int doUpdate(SqnNews t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<SqnNews> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<SqnNews> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
    
    void doInsertNews(SqnNews t, String createId, HttpServletRequest request) throws ServiceException;
    int doDelete(Map<String, Object> condition) throws ServiceException;
    
}
