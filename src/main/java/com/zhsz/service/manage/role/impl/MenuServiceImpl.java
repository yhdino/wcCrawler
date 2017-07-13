package com.zhsz.service.manage.role.impl;

import com.zhsz.dao.entity.manage.role.MenuEntity;
import com.zhsz.dao.mapper.manage.role.IMenuMapper;
import com.zhsz.dao.mapper.manage.role.IMenuRoleMapper;
import com.zhsz.service.manage.role.IMenuService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.persistent.ResultList;
import com.zhsz.utils.persistent.ResultListImpl;
import com.zhsz.utils.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = IMenuService.SERVICE_NAME)
@Transactional
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private IMenuMapper menuMapper;

	@Autowired
	private IMenuRoleMapper menuRoleMapper;

	@Override
	public ResultList<MenuEntity> queryMenus(String parentCode, Pagination page)
			throws ServiceException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("parentCode", parentCode);
			ResultList<MenuEntity> res = new ResultListImpl<MenuEntity>();
			if (page != null) {
				Long totalCount = menuMapper.menuCount(condition);
				page.setTotalCount(totalCount.intValue());

				condition.put("minnum", page.getStartNo());
				condition.put("maxnum", page.getEndNo());
			}
			List<MenuEntity> list = menuMapper.queryMenus(condition);

			res.setPage(page);
			res.setResults(list);
			return res;
		} catch (SQLException e) {
			ZhszLogger.error("获取系统菜单错误", e, this.getClass());
			throw new ServiceException("获取系统菜单错误", e);
		}

	}

	@Override
	public MenuEntity getMenu(String code) throws ServiceException {
		try {
			MenuEntity menu = null;
			menu = menuMapper.getMenu(code);
			return menu;
		} catch (SQLException e) {
			ZhszLogger.error("获取系统菜单错误", e, this.getClass());
			throw new ServiceException("获取系统菜单错误", e);
		}

	}

	@Override
	public void addMenu(MenuEntity menu) throws ServiceException {
		try {
			menuMapper.insertMenu(menu);
			if (!menu.getParentCode().equals("0")) {
				MenuEntity parentMenu = menuMapper
						.getMenu(menu.getParentCode());
				parentMenu.setChildrenCount(parentMenu.getChildrenCount() + 1);
				menuMapper.updateMenu(parentMenu);
			}
		} catch (SQLException e) {
			ZhszLogger.error("添加系统菜单错误", e, this.getClass());
			throw new ServiceException("添加系统菜单错误", e);
		}

	}

	@Override
	public void modifyMenu(MenuEntity menu) throws ServiceException {
		try {
			menuMapper.updateMenu(menu);
		} catch (SQLException e) {
			ZhszLogger.error("修改系统菜单错误", e, this.getClass());
			throw new ServiceException("修改系统菜单错误", e);
		}
	}

	@Override
	public void deleteMenus(String[] codes, String parentCode)
			throws ServiceException {
		try {
		    String code = null;
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("codes", new String[] { parentCode });
			condition.put("childrenCount", -(codes.length));
			menuMapper.updateChildrenCount(condition);
			for (int i = 0; i < codes.length; i++) {
			    code = codes[i];
			    menuMapper.deleteMenu(code);
			    menuRoleMapper.deleteMenuRolesByMenu(code);
			}
		} catch (SQLException e) {
			ZhszLogger.error("删除系统菜单错误", e, this.getClass());
			throw new ServiceException("删除系统菜单错误", e);
		}
	}

}
