package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.SqnRegisterCompany;
import com.zhsz.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * sqn_register_company数据库表名称
 * SqnRegisterCompany业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-06-05 15:31:30 
 */  
public interface SqnRegisterCompanyService{
    int doInsert(SqnRegisterCompany t) throws ServiceException;
    int doUpdate(SqnRegisterCompany t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<SqnRegisterCompany> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<SqnRegisterCompany> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
}
