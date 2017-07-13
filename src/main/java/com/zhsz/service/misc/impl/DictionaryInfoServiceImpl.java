package com.zhsz.service.misc.impl;

import com.zhsz.dao.entity.misc.DictionaryInfo;
import com.zhsz.dao.mapper.misc.DictionaryInfoMapper;
import com.zhsz.service.misc.DictionaryInfoService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * dictionary_info数据库表名称
 * DictionaryInfo业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
@Service
public class DictionaryInfoServiceImpl implements DictionaryInfoService {
    // 引用DAO层
    @Autowired
    private DictionaryInfoMapper dictionaryInfoMapper;
	
    @Override
	public int doInsert(DictionaryInfo t) throws ServiceException {
		try {
			return dictionaryInfoMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, DictionaryInfoServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(DictionaryInfo t) throws ServiceException {
		try {
			return dictionaryInfoMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, DictionaryInfoServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public long doQueryCountByCondition(Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DictionaryInfo> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DictionaryInfo> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		try {
			return dictionaryInfoMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, DictionaryInfoServiceImpl.class);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public long doQueryDictionaryInfoCount(Map<String, Object> condition) throws ServiceException{
		try {
			return dictionaryInfoMapper.doQueryDictionaryInfoCount(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, DictionaryInfoServiceImpl.class);
			throw new ServiceException(e);
		}
	}
	
	@Override
    public List<DictionaryInfo> doQueryDictionaryInfo(Map<String, Object> condition) throws ServiceException{
		try {
			return dictionaryInfoMapper.doQueryDictionaryInfo(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, DictionaryInfoServiceImpl.class);
			throw new ServiceException(e);
		}
	}
	
	@Override
    public List<DictionaryInfo> doQueryDictionaryInfoNoPage(Map<String, Object> condition) throws ServiceException{
		try {
			return dictionaryInfoMapper.doQueryDictionaryInfoNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, DictionaryInfoServiceImpl.class);
			throw new ServiceException(e);
		}
	}
	
	@Override
    public int deleteById(Map<String, Object> condition) throws ServiceException{
		try {
			return dictionaryInfoMapper.deleteById(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, DictionaryInfoServiceImpl.class);
			throw new ServiceException(e);
		}
	}
}
