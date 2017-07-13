package com.zhsz.service.manage.role;

import com.zhsz.dao.entity.manage.role.MenuEntity;

import com.zhsz.dao.role.MenuRoleModel;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.persistent.ResultList;

import java.util.List;

public interface IMenuRoleService {

	public static final String SERVICE_NAME = "com/zhsz/service/manage/role/IMenuRoleService";

	public ResultList<MenuEntity> queryUserMenus(String operatorId,
												 String parentCode) throws ServiceException;

	public ResultList<MenuRoleModel> queryCheckMenus(String roleId,
													 String parentCode) throws ServiceException;

	public void addMenuToRole(String roleCode, String[] menuIds)
			throws ServiceException;

	public List<MenuEntity> queryMenuRoles(String roleCode) throws ServiceException;

}
