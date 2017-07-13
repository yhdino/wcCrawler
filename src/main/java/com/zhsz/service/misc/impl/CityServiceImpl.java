package com.zhsz.service.misc.impl;

import com.zhsz.dao.entity.misc.City;
import com.zhsz.dao.mapper.misc.CityMapper;
import com.zhsz.service.misc.CityService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * city数据库表名称
 * City业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-06-01 14:35:08 
 */  
@Service
public class CityServiceImpl implements CityService {
    // 引用DAO层
    @Autowired
    private CityMapper cityMapper;
	
    @Override
	public int doInsert(City t) throws ServiceException {
		try {
			return cityMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, CityServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(City t) throws ServiceException {
		try {
			return cityMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, CityServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public long doQueryCountByCondition(Map<String, Object> condition)
			throws ServiceException {
		try {
			return cityMapper.doQueryCountByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, CityServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<City> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		try {
			return cityMapper.doQueryByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, CityServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<City> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		try {
			return cityMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, CityServiceImpl.class);
			throw new ServiceException(e);
		}
	}
}
