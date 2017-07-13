package com.zhsz.dao.mapper.manage.role;

import com.zhsz.dao.entity.manage.role.OperatorEntity;
import com.zhsz.dao.entity.manage.role.OperatorRoleEntity;

import java.sql.SQLException;
import java.util.List;

public interface IOperatorRoleMapper {
	
	public List<OperatorRoleEntity> queryOperatorRoles(String operatorId) throws SQLException;
	
	public List<OperatorEntity> searchOperatorRoles(String roleCode) throws SQLException;
	
	public void deleteOperatorRole(String roleCode) throws SQLException;
	
	public void deleteRoleByOperator(String operatorId) throws SQLException;
	
	public void insertOperatorRole(OperatorRoleEntity operatorRole)throws SQLException;
	
	public List<OperatorEntity> searchOperators(String roleCode) throws SQLException;
}
