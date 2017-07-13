package com.zhsz.service.misc.impl;

import com.zhsz.dao.entity.misc.Projectnews;
import com.zhsz.dao.mapper.misc.ProjectnewsMapper;
import com.zhsz.service.misc.ProjectnewsService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * projectnews数据库表名称
 * Projectnews业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-06-01 14:35:08 
 */  
@Service
public class ProjectnewsServiceImpl implements ProjectnewsService {
    // 引用DAO层
    @Autowired
    private ProjectnewsMapper projectnewsMapper;
	
    @Override
	public int doInsert(Projectnews t) throws ServiceException {
		try {
			return projectnewsMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ProjectnewsServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(Projectnews t) throws ServiceException {
		try {
			return projectnewsMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ProjectnewsServiceImpl.class);
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
	public List<Projectnews> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projectnews> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
