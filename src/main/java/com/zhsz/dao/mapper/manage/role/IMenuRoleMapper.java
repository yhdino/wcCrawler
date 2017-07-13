package com.zhsz.dao.mapper.manage.role;

import com.zhsz.dao.entity.manage.role.MenuEntity;
import com.zhsz.dao.entity.manage.role.MenuRoleEntity;
import com.zhsz.dao.role.MenuRoleModel;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IMenuRoleMapper {

	public List<MenuEntity> queryMenuRoles(String roleCode) throws SQLException;

	public List<MenuEntity> queryUserMenus(Map<String, Object> condition)
			throws SQLException;
	
	public List<MenuRoleEntity> searchMenuRoles(String roleCode) throws SQLException;
	
	public void deleteMenuRoles(String roleCode) throws SQLException;
	
	public void deleteMenuRolesByMenu(String menuCodes) throws SQLException;
	
	public void insertMenuRoles(Map<String, Object> condition) throws SQLException;
	
	public List<MenuRoleModel> queryCheckMenus(Map<String, Object> condition) throws SQLException;
}
