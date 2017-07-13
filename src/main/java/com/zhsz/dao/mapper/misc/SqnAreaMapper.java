package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.SqnArea;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * zhyq_area数据库表名称
 * ZhyqArea实体类
 * @author 刘浩
 * @Date Date: 2016-04-19 11:03:43 
 */  
public interface SqnAreaMapper {
    int doInsert(SqnArea t) throws SQLException;
    int doUpdate(SqnArea t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<SqnArea> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<SqnArea> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
    int DeleteById(Map<String, Object> condition) throws SQLException;
}