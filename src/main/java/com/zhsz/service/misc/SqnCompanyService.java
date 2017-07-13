package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.SqnCompany;
import com.zhsz.utils.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * sqn_company数据库表名称
 * SqnCompany业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-06-01 14:35:08 
 */  
public interface SqnCompanyService{
    void doInsert(SqnCompany t, String createId, HttpServletRequest request) throws ServiceException;
    int doUpdate(SqnCompany t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<SqnCompany> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<SqnCompany> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
    int doDelete(Map<String, Object> condition) throws ServiceException;
    
    List<SqnCompany> doQueryUnbindNoPage(Map<String, Object> condition) throws ServiceException;
}
