package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.SqnNews;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * sqn_news数据库表名称
 * SqnNews实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-07 14:53:36 
 */  
public interface SqnNewsMapper {
    int doInsert(SqnNews t) throws SQLException;
    int doUpdate(SqnNews t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<SqnNews> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<SqnNews> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
    
    int doDelete(Map<String, Object> condition) throws SQLException;
}