package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.City;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * city数据库表名称
 * City实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-01 14:35:08 
 */  
public interface CityMapper {
    int doInsert(City t) throws SQLException;
    int doUpdate(City t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<City> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<City> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
}