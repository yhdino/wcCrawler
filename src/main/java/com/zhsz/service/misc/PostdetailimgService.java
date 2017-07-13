package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.Postdetailimg;
import com.zhsz.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * postdetailimg数据库表名称
 * Postdetailimg业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-06-26 17:32:56 
 */  
public interface PostdetailimgService{
    int doInsert(Postdetailimg t) throws ServiceException;
    int doUpdate(Postdetailimg t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<Postdetailimg> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<Postdetailimg> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
}
