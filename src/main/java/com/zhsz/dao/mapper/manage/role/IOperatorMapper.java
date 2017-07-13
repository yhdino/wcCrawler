package com.zhsz.dao.mapper.manage.role;

import com.zhsz.dao.entity.manage.role.OperatorEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IOperatorMapper {

	public OperatorEntity getOperator(String operatorId) throws SQLException;

	public void insertOperator(OperatorEntity operator) throws SQLException;

	public void updateOperator(OperatorEntity operator) throws SQLException;

	public void deleteOperator(String operatorId) throws SQLException;

	public Long operatorCount(Map<String, Object> condition) throws SQLException;  
	
	public List<OperatorEntity> queryOperators(Map<String, Object> condition)
			throws SQLException;
	
	public List<OperatorEntity> userLogin(Map<String, Object> condition)
			throws SQLException;

}