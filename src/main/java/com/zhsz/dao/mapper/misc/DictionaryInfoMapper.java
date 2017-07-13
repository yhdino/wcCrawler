package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.DictionaryInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * dictionary_info数据库表名称
 * DictionaryInfo实体类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public interface DictionaryInfoMapper {
    int doInsert(DictionaryInfo t) throws SQLException;
    int doUpdate(DictionaryInfo t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<DictionaryInfo> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<DictionaryInfo> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
    
    long doQueryDictionaryInfoCount(Map<String, Object> condition) throws SQLException;
    List<DictionaryInfo> doQueryDictionaryInfo(Map<String, Object> condition) throws SQLException;
    List<DictionaryInfo> doQueryDictionaryInfoNoPage(Map<String, Object> condition) throws SQLException;
    
    int deleteById(Map<String, Object> condition) throws SQLException;
}