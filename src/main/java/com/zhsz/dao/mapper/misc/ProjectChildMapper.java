package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.ProjectChild;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * project_child数据库表名称
 * ProjectChild实体类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public interface ProjectChildMapper {
    int doInsert(ProjectChild t) throws SQLException;
    int doUpdate(ProjectChild t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<ProjectChild> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<ProjectChild> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
    List<ProjectChild> doQueryListNoPage(Map<String, Object> condition) throws SQLException;
    
    List<ProjectChild> doQueryAll(Map<String, Object> condition) throws SQLException;
}