package com.zhsz.service.misc.impl;

import com.zhsz.dao.entity.misc.Project;
import com.zhsz.dao.mapper.misc.ProjectMapper;
import com.zhsz.service.misc.ProjectService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * project数据库表名称
 * Project业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
@Service
public class ProjectServiceImpl implements ProjectService {
    // 引用DAO层
    @Autowired
    private ProjectMapper projectMapper;
	
    @Override
	public int doInsert(Project t) throws ServiceException {
		try {
			return projectMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ProjectServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(Project t) throws ServiceException {
		try {
			return projectMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ProjectServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public long doQueryCountByCondition(Map<String, Object> condition)
			throws ServiceException {
		try {
			return projectMapper.doQueryCountByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ProjectServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Project> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		try {
			return projectMapper.doQueryByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ProjectServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Project> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		try {
			return projectMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ProjectServiceImpl.class);
			throw new ServiceException(e);
		}
	}
}
