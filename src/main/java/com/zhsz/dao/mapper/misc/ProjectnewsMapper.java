package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.Projectnews;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * projectnews数据库表名称
 * Projectnews实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-01 14:35:08 
 */  
public interface ProjectnewsMapper {
    int doInsert(Projectnews t) throws SQLException;
    int doUpdate(Projectnews t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<Projectnews> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<Projectnews> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
}