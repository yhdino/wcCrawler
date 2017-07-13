package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * post数据库表名称
 * Post实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-08 10:05:47 
 */  
public interface PostMapper {
    int doInsert(Post t) throws SQLException;
    int doUpdate(Post t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<Post> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<Post> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
    
    int doInsertTest1() throws SQLException;
    List<Post> doQueryByPostDetail(Map<String, Object> condition) throws SQLException;
}