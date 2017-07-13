package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.Project;
import com.zhsz.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * project数据库表名称
 * Project业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public interface ProjectService{
    int doInsert(Project t) throws ServiceException;
    int doUpdate(Project t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<Project> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<Project> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
}
