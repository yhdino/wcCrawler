package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.Group;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * group数据库表名称
 * Group实体类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public interface GroupMapper {
    int doInsert(Group t) throws SQLException;
    int doUpdate(Group t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<Group> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<Group> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
}