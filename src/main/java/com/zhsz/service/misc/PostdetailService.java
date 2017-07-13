package com.zhsz.service.misc;

import java.util.List;
import java.util.Map;

import com.zhsz.dao.entity.misc.Postdetail;
import com.zhsz.utils.exception.ServiceException;

/**
 * postdetail数据库表名称
 * Postdetail业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-06-16 15:32:57 
 */  
public interface PostdetailService{
    int doInsert(Postdetail t) throws ServiceException;
    int doUpdate(Postdetail t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<Postdetail> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<Postdetail> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
    
    int doInsertPostDetail(String content,String url,String json) throws ServiceException;
    List<Postdetail> doQueryByPostImg(Map<String, Object> condition) throws ServiceException;
    
    List<Postdetail> doQueryByTheme(Map<String, Object> condition) throws ServiceException;
}
