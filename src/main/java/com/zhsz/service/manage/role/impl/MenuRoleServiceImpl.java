package com.zhsz.service.manage.role.impl;

import com.zhsz.dao.entity.manage.role.MenuEntity;
import com.zhsz.dao.entity.manage.role.MenuRoleEntity;
import com.zhsz.dao.role.MenuRoleModel;
import com.zhsz.dao.mapper.manage.role.IMenuRoleMapper;
import com.zhsz.service.manage.role.IMenuRoleService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.persistent.ResultList;
import com.zhsz.utils.persistent.ResultListImpl;
import com.zhsz.utils.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(IMenuRoleService.SERVICE_NAME)
public class MenuRoleServiceImpl implements IMenuRoleService {

	@Autowired
	private IMenuRoleMapper menuRoleMapper;

	@Override
	@Transactional(readOnly = true)
	public ResultList<MenuEntity> queryUserMenus(String operatorId,
			String parentCode) throws ServiceException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("operatorId", operatorId);
			condition.put("parentCode", parentCode);
			List<MenuEntity> menus = menuRoleMapper.queryUserMenus(condition);
			ResultList<MenuEntity> result = new ResultListImpl<MenuEntity>();
			result.setResults(menus);
			return result;
		} catch (SQLException e) {
			ZhszLogger.error("获取用户菜单错误", e, MenuRoleServiceImpl.class);
			throw new ServiceException("获取用户菜单错误", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ResultList<MenuRoleModel> queryCheckMenus(String roleId,
			String parentCode) throws ServiceException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("roleCode", roleId);
			condition.put("parentCode", parentCode);
			List<MenuRoleModel> list = menuRoleMapper
					.queryCheckMenus(condition);
			ResultList<MenuRoleModel> result = new ResultListImpl<MenuRoleModel>();
			result.setResults(list);
			return result;
		} catch (SQLException e) {
			ZhszLogger.error("获取菜单错误", e, MenuRoleServiceImpl.class);
			throw new ServiceException("获取菜单错误", e);
		}

	}

	@Override
	@Transactional
	public void addMenuToRole(String roleCode, String[] menuIds)
			throws ServiceException {
		try {
			List<MenuRoleEntity> menuroles = new ArrayList<MenuRoleEntity>();
			//menuRoleMapper.deleteMenuRoles(new String[]{roleCode});
			if (menuIds != null) {
				for (int i = 0; i < menuIds.length; i++) {
				    menuRoleMapper.deleteMenuRoles(roleCode);
					MenuRoleEntity menurole = new MenuRoleEntity();
					menurole.setId(StringUtils.getUUID());
					menurole.setRoleCode(roleCode);
					menurole.setMenuCode(menuIds[i]);
					menuroles.add(menurole);

				}
				for(MenuRoleEntity menurole:menuroles){
				    Map<String, Object> condition = new HashMap<String, Object>();
	                condition.put("id", menurole.getId());
	                condition.put("menuCode", menurole.getMenuCode());
	                condition.put("roleCode", menurole.getRoleCode());
	                menuRoleMapper.insertMenuRoles(condition);
				}
			}
		} catch (Exception e) {
			ZhszLogger.error("添加权限菜单错误", e, MenuRoleServiceImpl.class);
			throw new ServiceException("添加权限菜单错误", e);
		}

	}

	@Override
	public List<MenuEntity> queryMenuRoles(String roleCode)
			throws ServiceException {
		try {
			return menuRoleMapper.queryMenuRoles(roleCode);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
