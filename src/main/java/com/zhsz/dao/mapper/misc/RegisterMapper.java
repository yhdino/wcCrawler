package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.Register;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * register数据库表名称
 * Register实体类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public interface RegisterMapper {
    int doInsert(Register t) throws SQLException;
    int doUpdate(Register t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<Register> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<Register> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
    
    long doQueryCountByProid(Map<String, Object> condition) throws SQLException;
    String doInsertRegister(Map<String, Object> condition) throws SQLException;
    
    List<Register> doQueryByConditionInfo(Map<String, Object> condition) throws SQLException;
    int doDelete(Register t) throws SQLException;
    
    long doQueryRegisterCount(Map<String, Object> condition) throws SQLException;
    List<Register> doQueryRegister(Map<String, Object> condition) throws SQLException;
    List<Register> doQueryRegisterNoPage(Map<String, Object> condition) throws SQLException;
    
    int deleteById(Map<String, Object> condition) throws SQLException;
}