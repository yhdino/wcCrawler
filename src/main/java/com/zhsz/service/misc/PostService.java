package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.Post;
import com.zhsz.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * post数据库表名称
 * Post业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-06-08 10:05:47 
 */  
public interface PostService{
    int doInsert(Post t) throws ServiceException;
    int doUpdate(Post t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<Post> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<Post> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
    
    
    int doInsertTest1(String json, String biz, String param) throws ServiceException;
    
    List<Post> doQueryByPostDetail(Map<String, Object> condition) throws ServiceException;
}
