package com.zhsz.service.misc.impl;

import com.zhsz.dao.entity.misc.SqnRegisterCompany;
import com.zhsz.dao.mapper.misc.SqnRegisterCompanyMapper;
import com.zhsz.service.misc.SqnRegisterCompanyService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * sqn_register_company数据库表名称
 * SqnRegisterCompany业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-06-05 15:31:30 
 */  
@Service
public class SqnRegisterCompanyServiceImpl implements SqnRegisterCompanyService {
    // 引用DAO层
    @Autowired
    private SqnRegisterCompanyMapper sqnRegisterCompanyMapper;
	
    @Override
	public int doInsert(SqnRegisterCompany t) throws ServiceException {
		try {
			return sqnRegisterCompanyMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnRegisterCompanyServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(SqnRegisterCompany t) throws ServiceException {
		try {
			return sqnRegisterCompanyMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnRegisterCompanyServiceImpl.class);
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
	public List<SqnRegisterCompany> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SqnRegisterCompany> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		try {
			return sqnRegisterCompanyMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnRegisterCompanyServiceImpl.class);
			throw new ServiceException(e);
		}
	}
}
