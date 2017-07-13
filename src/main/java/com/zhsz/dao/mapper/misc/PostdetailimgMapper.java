package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.Postdetailimg;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * postdetailimg数据库表名称
 * Postdetailimg实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-26 17:32:56 
 */  
public interface PostdetailimgMapper {
    int doInsert(Postdetailimg t) throws SQLException;
    int doUpdate(Postdetailimg t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<Postdetailimg> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<Postdetailimg> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
}