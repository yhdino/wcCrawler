package com.zhsz.service.misc.impl;

import com.zhsz.dao.entity.misc.Postdetailimg;
import com.zhsz.dao.mapper.misc.PostdetailimgMapper;
import com.zhsz.service.misc.PostdetailimgService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * postdetailimg数据库表名称
 * Postdetailimg业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-06-26 17:32:56 
 */  
@Service
public class PostdetailimgServiceImpl implements PostdetailimgService {
    // 引用DAO层
    @Autowired
    private PostdetailimgMapper postdetailimgMapper;
	
    @Override
	public int doInsert(Postdetailimg t) throws ServiceException {
		try {
			return postdetailimgMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostdetailimgServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(Postdetailimg t) throws ServiceException {
		try {
			return postdetailimgMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostdetailimgServiceImpl.class);
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
	public List<Postdetailimg> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Postdetailimg> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
