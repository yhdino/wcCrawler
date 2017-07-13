package com.zhsz.service.manage.role;

import com.zhsz.dao.entity.manage.role.MenuEntity;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.persistent.ResultList;
import com.zhsz.utils.utils.page.Pagination;

public interface IMenuService {

	public static final String SERVICE_NAME = "com/zhsz/service/manage/role/IMenuService";

	/**
	 * 根据父节点查询子菜单
	 * **/
	public ResultList<MenuEntity> queryMenus(String parentCode, Pagination page)
			throws ServiceException;

	/**
	 * 根据code查询功能菜单信息
	 * */
	public MenuEntity getMenu(String code) throws ServiceException;

	/**
	 * 添加菜单信息
	 * */
	public void addMenu(MenuEntity menu) throws ServiceException;

	/**
	 * 修改菜单信息
	 * */

	public void modifyMenu(MenuEntity menu) throws ServiceException;

	/**
	 * 根据ID删除菜单信息
	 * */
	public void deleteMenus(String[] codes, String parentCodes) throws ServiceException;
}
