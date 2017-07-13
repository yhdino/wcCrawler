package com.zhsz.service.manage.role.impl;

import com.zhsz.dao.entity.manage.role.OperatorEntity;
import com.zhsz.dao.entity.manage.role.OperatorRoleEntity;
import com.zhsz.dao.mapper.manage.role.IOperatorRoleMapper;
import com.zhsz.service.manage.role.IOperatorRoleService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service(value = IOperatorRoleService.SERVICE_NAME)

public class OperatorRoleServiceImpl implements IOperatorRoleService {
	
	@Autowired
	private IOperatorRoleMapper operatorRoleMapper;
	
	
	@Override
	@Transactional(readOnly=true)
	public List<OperatorEntity> searchOperatorRoles(String roleCode)
			throws ServiceException {
		List<OperatorEntity> operatorRoles=null;
		try {
			operatorRoles = operatorRoleMapper.searchOperatorRoles(roleCode);
		} catch (SQLException e) {
			ZhszLogger.error("查询该角色的操作员信息失败", e,this.getClass());
			throw new ServiceException("查询该角色的操作员信息失败",e);
		}
		return operatorRoles;
	}
	@Override
	@Transactional
	public void addOperatorRole(OperatorRoleEntity operatorRole)
			throws ServiceException {
		try {
            operatorRole.setId(StringUtils.getUUID());
            operatorRoleMapper.insertOperatorRole(operatorRole);
		} catch (SQLException e) {
			ZhszLogger.error("添加该角色的操作员信息失败", e,this.getClass());
			throw new ServiceException("添加该角色的操作员信息失败",e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<OperatorEntity> searchOperators(String roleCode)
			throws ServiceException {
		List<OperatorEntity> operatorRoles=null;
		try {
			operatorRoles = operatorRoleMapper.searchOperators(roleCode);
		} catch (SQLException e) {
			ZhszLogger.error("查询该角色的操作员信息失败", e,this.getClass());
			throw new ServiceException("查询该角色的操作员信息失败",e);
		}
		return operatorRoles;
	}
	@Override
	public List<OperatorRoleEntity> queryOperatorRoles(String operatorId)
			throws SQLException {
		List<OperatorRoleEntity> operatorRoles=null;
		try {
			operatorRoles = operatorRoleMapper.queryOperatorRoles(operatorId);
		} catch (SQLException e) {
			ZhszLogger.error("查询该角色的操作员信息失败", e,this.getClass());
			throw new ServiceException("查询该角色的操作员信息失败",e);
		}
		return operatorRoles;
	}

}
