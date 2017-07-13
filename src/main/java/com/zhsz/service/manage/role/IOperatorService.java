package com.zhsz.service.manage.role;

import com.zhsz.dao.entity.manage.role.OperatorEntity;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.exception.SystemException;
import com.zhsz.utils.persistent.ResultList;
import com.zhsz.utils.utils.page.Pagination;

import java.util.List;
import java.util.Map;

public interface IOperatorService {

	public static final String SERVICE_NAME = "com/zhsz/service/manage/role/IOperatorService";
	
	/**
	 *添加操作员信息 
	 * **/
	public void addOperator(OperatorEntity operator, String[] roleCode) throws ServiceException;

	/**
	 *根据条件查询操作员信息
	 * **/
	public ResultList<OperatorEntity> queryOperators(String operatorId,
													 String name, String status, Pagination page) throws ServiceException;
	
	/**
	 * 根据账号名查询操作员信息
	 * */
	public OperatorEntity getOperatorById(String operatorId) throws ServiceException;


	/**
	 * 修改操作员信息
	 * */
	
	public void modifyOperator(OperatorEntity operator)throws ServiceException;
	

	/**
	 * 修改操作员密码
	 * */
	
	public void modifyOperatorPwd(String operatorId, String password,
								  String oldPassword)throws ServiceException, SystemException;

	/**
	 * 根据ID删除操作员信息
	 * */
	public void deleteOperator(String operatorId)throws ServiceException;
	

	/**
	 * 删除已选的操作员信息
	 * */
	public void deleteOperators(String[] ids)throws ServiceException;
	
	
	public List<OperatorEntity> userLogin(Map<String, Object> condition)throws ServiceException;
	
}
