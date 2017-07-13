package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.SqnCompany;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * sqn_company数据库表名称
 * SqnCompany实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-01 14:35:08 
 */  
public interface SqnCompanyMapper {
    int doInsert(SqnCompany t) throws SQLException;
    int doUpdate(SqnCompany t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<SqnCompany> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<SqnCompany> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
    int doDelete(Map<String, Object> condition) throws SQLException;
    
    List<SqnCompany> doQueryUnbindNoPage(Map<String, Object> condition) throws SQLException;
}