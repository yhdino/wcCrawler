package com.zhsz.service.manage.role;

import com.zhsz.dao.entity.manage.role.OperatorEntity;
import com.zhsz.dao.entity.manage.role.OperatorRoleEntity;
import com.zhsz.utils.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface IOperatorRoleService {

	public static final String SERVICE_NAME = "com/zhsz/service/manage/role/IOperatorRoleService";
	
	/*
	 * 获取操作员角色信息
	 * **/
	
	public List<OperatorEntity> searchOperatorRoles(String roleCode) throws ServiceException;
	
	/*
	 * 添加操作员角色信息
	 * **/
	public void addOperatorRole(OperatorRoleEntity operatorRole)throws ServiceException;
	
	/*
	 * 获取操作员角色信息
	 * **/
	
	public List<OperatorEntity> searchOperators(String roleCode) throws ServiceException;
	
	/*
	 * 根据operator_id获取roleCode 
	 */
	public List<OperatorRoleEntity> queryOperatorRoles(String operatorId) throws SQLException;
}
