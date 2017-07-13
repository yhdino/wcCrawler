package com.zhsz.service.misc.impl;

import com.zhsz.dao.entity.misc.ProjectChild;
import com.zhsz.dao.mapper.misc.ProjectChildMapper;
import com.zhsz.service.misc.ProjectChildService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * project_child数据库表名称
 * ProjectChild业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
@Service
public class ProjectChildServiceImpl implements ProjectChildService {
    // 引用DAO层
    @Autowired
    private ProjectChildMapper projectChildMapper;
	
    @Override
	public int doInsert(ProjectChild t) throws ServiceException {
		try {
			return projectChildMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ProjectChildServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(ProjectChild t) throws ServiceException {
		try {
			return projectChildMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ProjectChildServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public long doQueryCountByCondition(Map<String, Object> condition)
			throws ServiceException {
		try {
			return projectChildMapper.doQueryCountByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, this.getClass());
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ProjectChild> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		try {
			return projectChildMapper.doQueryByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, this.getClass());
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ProjectChild> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		try {
			return projectChildMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, this.getClass());
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ProjectChild> doQueryListNoPage(Map<String, Object> condition) throws ServiceException {
		try {
			return projectChildMapper.doQueryListNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, this.getClass());
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ProjectChild> doQueryAll(Map<String, Object> condition) throws ServiceException {
		try {
			return projectChildMapper.doQueryAll(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, this.getClass());
			throw new ServiceException(e);
		}
	}
}
