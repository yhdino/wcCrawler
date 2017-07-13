package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.Theme;
import com.zhsz.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * theme数据库表名称
 * Theme业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-06-22 15:32:06 
 */  
public interface ThemeService{
    int doInsert(Theme t) throws ServiceException;
    int doUpdate(Theme t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<Theme> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<Theme> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
}
