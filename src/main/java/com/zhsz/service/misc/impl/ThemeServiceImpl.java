package com.zhsz.service.misc.impl;

import com.zhsz.dao.entity.misc.Theme;
import com.zhsz.dao.mapper.misc.ThemeMapper;
import com.zhsz.service.misc.ThemeService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * theme数据库表名称
 * Theme业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-06-22 15:32:06 
 */  
@Service
public class ThemeServiceImpl implements ThemeService {
    // 引用DAO层
    @Autowired
    private ThemeMapper themeMapper;
	
    @Override
	public int doInsert(Theme t) throws ServiceException {
		try {
			return themeMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ThemeServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(Theme t) throws ServiceException {
		try {
			return themeMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ThemeServiceImpl.class);
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
	public List<Theme> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Theme> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		try {
			return themeMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ThemeServiceImpl.class);
			throw new ServiceException(e);
		}
	}
}
