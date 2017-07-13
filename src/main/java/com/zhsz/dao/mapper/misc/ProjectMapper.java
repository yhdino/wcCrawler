package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.Project;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * project数据库表名称
 * Project实体类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public interface ProjectMapper {
    int doInsert(Project t) throws SQLException;
    int doUpdate(Project t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<Project> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<Project> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
}