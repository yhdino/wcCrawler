package com.zhsz.service.manage.role.impl;

import com.zhsz.dao.entity.manage.role.OperatorEntity;
import com.zhsz.dao.entity.manage.role.OperatorRoleEntity;
import com.zhsz.dao.mapper.manage.role.IOperatorMapper;
import com.zhsz.dao.mapper.manage.role.IOperatorRoleMapper;
import com.zhsz.service.manage.role.IOperatorService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.exception.SystemException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.persistent.ResultList;
import com.zhsz.utils.persistent.ResultListImpl;
import com.zhsz.utils.utils.EncryptUtil;
import com.zhsz.utils.utils.StringUtils;
import com.zhsz.utils.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = IOperatorService.SERVICE_NAME)
public class OperatorServiceImpl implements IOperatorService {

	@Autowired
	private IOperatorMapper operatorMapper;

	@Autowired
	private IOperatorRoleMapper operatorRoleMapper;

	@Override
	@Transactional
	public void addOperator(OperatorEntity operator, String[] roleCodes)
			throws ServiceException {
		try {
			
			if(roleCodes[0].equals("ROLE_ADMIN")){
				operator.setAreaCode("");
				operator.setAreaName("");
				operator.setType("0");
			}
			operatorMapper.insertOperator(operator);
			OperatorRoleEntity ore = new OperatorRoleEntity();
			if (roleCodes != null) {
				List<OperatorRoleEntity> operatorRoles = new ArrayList<OperatorRoleEntity>();
				for (int i = 0; i < roleCodes.length; i++) {
                    ore.setId(StringUtils.getUUID());
                    ore.setOperatorId(operator.getOperatorId());
                    ore.setRoleCode(roleCodes[i]);
                    operatorRoles.add(ore);
                    operatorRoleMapper.insertOperatorRole(ore);
				}
			}
		} catch (SQLException e) {
			ZhszLogger.error("添加操作员错误", e, this.getClass());
			throw new ServiceException("添加操作员错误", e);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public ResultList<OperatorEntity> queryOperators(String operatorId,
			String name, String status, Pagination page)
			throws ServiceException {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("operatorId", operatorId);
		condition.put("name", name);
		condition.put("status", status);
		Long totalCount;
		ResultList<OperatorEntity> res = new ResultListImpl<OperatorEntity>();
		try {
			if (page != null) {
				totalCount = operatorMapper.operatorCount(condition);
				page.setTotalCount(totalCount.intValue());

				condition.put("minnum", page.getStartNo());
				condition.put("maxnum", page.getEndNo());

			}
			List<OperatorEntity> list;

			list = operatorMapper.queryOperators(condition);

			res.setPage(page);
			res.setResults(list);
		} catch (SQLException e) {
			ZhszLogger.error("查询操作员错误", e, this.getClass());
			throw new ServiceException("查询操作员错误", e);
		}
		return res;

	}

	@Override
	@Transactional(readOnly = true)
	public OperatorEntity getOperatorById(String operatorId)
			throws ServiceException {
		OperatorEntity operator = null;
		try {
			operator = operatorMapper.getOperator(operatorId);
		} catch (SQLException e) {
			ZhszLogger.error("获得操作员信息失败", e, this.getClass());
			throw new ServiceException("获得操作员信息失败", e);
		}
		return operator;
	}

	@Override
	@Transactional
	public void modifyOperator(OperatorEntity operator) throws ServiceException {
		try {
			if (operator != null) {
				operatorMapper.updateOperator(operator);
			}
		} catch (SQLException e) {
			ZhszLogger.error("修改操作员信息失败", e, this.getClass());
			throw new ServiceException("修改操作员信息失败", e);
		}

	}

	@Override
	@Transactional
	public void modifyOperatorPwd(String operatorId, String password,
			String oldPassword) throws ServiceException, SystemException {
		try {
			OperatorEntity oldoperator = operatorMapper.getOperator(operatorId);
			if (oldoperator != null) {
				String oldPasswordMd5 = EncryptUtil.md5Hex(oldPassword);
				if (!oldPasswordMd5.equals(oldoperator.getPassword())) {
					throw new SystemException("原密码错误");
				}
				oldoperator.setPassword(EncryptUtil.md5Hex(password));
				operatorMapper.updateOperator(oldoperator);
			}
		} catch (SQLException e) {
			ZhszLogger.error("修改操作员密码失败", e, this.getClass());
			throw new ServiceException("修改操作员密码失败", e);
		}

	}

	@Override
	@Transactional
	public void deleteOperator(String operatorId) throws ServiceException {
		try {
			if (operatorId != null) {
			    operatorMapper.deleteOperator(operatorId);
			}
		} catch (Exception e) {
			ZhszLogger.error("删除操作员信息失败", e, this.getClass());
			throw new ServiceException("删除操作员信息失败", e);
		}

	}

	@Override
	@Transactional
	public void deleteOperators(String[] ids) throws ServiceException {
		try {
		    String id;
		    for(int i=0; i<ids.length; i++){
		        id = ids[i];
		        operatorMapper.deleteOperator(id);
	            operatorRoleMapper.deleteRoleByOperator(id);
		    }
		} catch (SQLException e) {
			ZhszLogger.error("删除操作员信息失败", e, this.getClass());
			throw new ServiceException("删除操作员信息失败", e);
		}
	}

	@Override
	public List<OperatorEntity> userLogin(Map<String, Object> condition)
			throws ServiceException {
		List<OperatorEntity> list = null;
    	try {
    		list = operatorMapper.userLogin(condition);
  		  return list;
		} catch (SQLException e) {
			ZhszLogger.error("", e, OperatorServiceImpl.class);
			throw new ServiceException(e);
		}
	}

}
