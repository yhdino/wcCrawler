package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.Projectnews;
import com.zhsz.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * projectnews数据库表名称
 * Projectnews业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-06-01 14:35:08 
 */  
public interface ProjectnewsService{
    int doInsert(Projectnews t) throws ServiceException;
    int doUpdate(Projectnews t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<Projectnews> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<Projectnews> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
}
