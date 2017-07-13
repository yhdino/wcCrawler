package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.SqnArea;
import com.zhsz.utils.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * zhyq_area数据库表名称
 * ZhyqArea业务接口类
 * @author 刘浩
 * @Date Date: 2016-04-19 11:03:43 
 */  
public interface SqnAreaService{
    void doInsert(SqnArea zhyqArea, String createId, HttpServletRequest request) throws ServiceException;
    int doUpdate(SqnArea t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<SqnArea> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<SqnArea> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
    int DeleteById(Map<String, Object> condition) throws SQLException;
}