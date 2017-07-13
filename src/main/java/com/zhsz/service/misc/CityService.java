package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.City;
import com.zhsz.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * city数据库表名称
 * City业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-06-01 14:35:08 
 */  
public interface CityService{
    int doInsert(City t) throws ServiceException;
    int doUpdate(City t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<City> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<City> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
}
