package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.Weixin;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * weixin数据库表名称
 * Weixin实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-08 10:05:47 
 */  
public interface WeixinMapper {
    int doInsert(Weixin t) throws SQLException;
    int doUpdate(Weixin t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<Weixin> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<Weixin> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
}