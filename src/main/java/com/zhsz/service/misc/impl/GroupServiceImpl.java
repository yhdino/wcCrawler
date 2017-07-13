package com.zhsz.service.misc.impl;

import com.zhsz.dao.entity.misc.Group;
import com.zhsz.dao.mapper.misc.GroupMapper;
import com.zhsz.service.misc.GroupService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * group数据库表名称
 * Group业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
@Service
public class GroupServiceImpl implements GroupService {
    // 引用DAO层
    @Autowired
    private GroupMapper groupMapper;
	
    @Override
	public int doInsert(Group t) throws ServiceException {
		try {
			return groupMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, GroupServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(Group t) throws ServiceException {
		try {
			return groupMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, GroupServiceImpl.class);
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
	public List<Group> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		try {
			return groupMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, this.getClass());
			throw new ServiceException(e);
		}
	}
}
