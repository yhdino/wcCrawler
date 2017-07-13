package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.Reply;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * reply数据库表名称
 * Reply实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-19 15:43:12 
 */  
public interface ReplyMapper {
    int doInsert(Reply t) throws SQLException;
    int doUpdate(Reply t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<Reply> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<Reply> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
    
    List<Reply> doQueryByReply(Map<String, Object> condition) throws SQLException;
}