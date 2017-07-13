package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.Register;
import com.zhsz.utils.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * register数据库表名称
 * Register业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public interface RegisterService{
    int doInsert(Register t) throws ServiceException;
    int doUpdate(Register t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<Register> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<Register> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
    
    long doQueryCountByProid(Map<String, Object> condition) throws ServiceException;
    
    String doInsertRegister(Map<String, Object> condition) throws ServiceException;
    
    List<Register> doQueryByConditionInfo(Map<String, Object> condition) throws ServiceException;
    
    int doDelete(Register t) throws ServiceException;
    
    long doQueryRegisterCount(Map<String, Object> condition) throws SQLException;
    List<Register> doQueryRegister(Map<String, Object> condition) throws SQLException;
    List<Register> doQueryRegisterNoPage(Map<String, Object> condition) throws SQLException;
    
    int deleteById(Map<String, Object> condition) throws SQLException;
}
