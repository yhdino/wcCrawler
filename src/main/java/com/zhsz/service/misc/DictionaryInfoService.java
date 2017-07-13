package com.zhsz.service.misc;

import com.zhsz.dao.entity.misc.DictionaryInfo;
import com.zhsz.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * dictionary_info数据库表名称
 * DictionaryInfo业务接口类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public interface DictionaryInfoService{
    int doInsert(DictionaryInfo t) throws ServiceException;
    int doUpdate(DictionaryInfo t) throws ServiceException;
    long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException;
    List<DictionaryInfo> doQueryByCondition(Map<String, Object> condition) throws ServiceException;
    List<DictionaryInfo> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException;
    
    long doQueryDictionaryInfoCount(Map<String, Object> condition) throws ServiceException;
    List<DictionaryInfo> doQueryDictionaryInfo(Map<String, Object> condition) throws ServiceException;
    List<DictionaryInfo> doQueryDictionaryInfoNoPage(Map<String, Object> condition) throws ServiceException;
    
    int deleteById(Map<String, Object> condition) throws ServiceException;
}
