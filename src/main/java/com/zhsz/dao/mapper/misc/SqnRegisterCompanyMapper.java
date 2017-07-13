package com.zhsz.dao.mapper.misc;

import com.zhsz.dao.entity.misc.SqnRegisterCompany;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * sqn_register_company数据库表名称
 * SqnRegisterCompany实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-05 15:31:30 
 */  
public interface SqnRegisterCompanyMapper {
    int doInsert(SqnRegisterCompany t) throws SQLException;
    int doUpdate(SqnRegisterCompany t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<SqnRegisterCompany> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<SqnRegisterCompany> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
}