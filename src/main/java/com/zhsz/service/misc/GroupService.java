package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.Group;
import com.zhsz.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * group数据库表名称
 * Group业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public interface GroupService{
    int doInsert(Group t) throws ServiceException;
    int doUpdate(Group t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<Group> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<Group> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
}
