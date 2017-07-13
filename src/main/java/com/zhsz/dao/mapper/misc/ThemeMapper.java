package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.Theme;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * theme数据库表名称
 * Theme实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-22 15:32:06 
 */  
public interface ThemeMapper {
    int doInsert(Theme t) throws SQLException;
    int doUpdate(Theme t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<Theme> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<Theme> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
}