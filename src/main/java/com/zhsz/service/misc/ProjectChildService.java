package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.ProjectChild;
import com.zhsz.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * project_child数据库表名称
 * ProjectChild业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public interface ProjectChildService{
    int doInsert(ProjectChild t) throws ServiceException;
    int doUpdate(ProjectChild t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<ProjectChild> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<ProjectChild> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
    
    
    List<ProjectChild> doQueryListNoPage(Map<String, Object> condition) throws ServiceException;
    List<ProjectChild> doQueryAll(Map<String, Object> condition) throws ServiceException;
}
