package com.zhsz.service.manage.role.impl;

import com.zhsz.dao.entity.manage.role.OperatorRoleEntity;
import com.zhsz.dao.entity.manage.role.RoleEntity;
import com.zhsz.dao.mapper.manage.role.IMenuRoleMapper;
import com.zhsz.dao.mapper.manage.role.IOperatorRoleMapper;
import com.zhsz.dao.mapper.manage.role.IRoleMapper;
import com.zhsz.service.manage.role.IRoleService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.persistent.ResultList;
import com.zhsz.utils.persistent.ResultListImpl;
import com.zhsz.utils.utils.StringUtils;
import com.zhsz.utils.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = IRoleService.SERVICE_NAME)
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleMapper roleMapper;

	@Autowired
	private IMenuRoleMapper menuRoleMapper;

	@Autowired
	private IOperatorRoleMapper operatorRoleMapper;

	@Override
	@Transactional(readOnly = true)
	public ResultList<RoleEntity> queryRoles(String title, Pagination page)
			throws ServiceException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			ResultList<RoleEntity> res = new ResultListImpl<RoleEntity>();
			if (StringUtils.isNotEmpty(title)) {
				condition.put("title", title);
			}
			if (page != null) {
				Long totalCount;
				totalCount = roleMapper.roleCount(condition);
				page.setTotalCount(totalCount.intValue());

				condition.put("minnum", page.getStartNo());
				condition.put("maxnum", page.getEndNo());
			}
			List<RoleEntity> list = roleMapper.searchRoles(condition);
			res.setPage(page);
			res.setResults(list);
			return res;
		} catch (SQLException e) {
			ZhszLogger.error("查询角色失败", e, this.getClass());
			throw new ServiceException("查询角色失败", e);
		}

	}
	@Override
	@Transactional(readOnly = true)
	public RoleEntity getRole(String code) throws ServiceException {
		RoleEntity role = null;
		try {
			role = roleMapper.getRole(code);
		} catch (SQLException e) {
			ZhszLogger.error("获得角色失败", e, this.getClass());
			throw new ServiceException("获得信息失败", e);
		}
		return role;
	}

	@Override
	@Transactional
	public void modifyRole(RoleEntity role) throws ServiceException {
		try {
			roleMapper.updateRole(role);
		} catch (SQLException e) {
			ZhszLogger.error("修改角色信息失败", e, this.getClass());
			throw new ServiceException("修改角色信息失败", e);
		}
	}

	@Override
	@Transactional
	public void addRole(RoleEntity role) throws ServiceException {
		try {
			roleMapper.insertRole(role);
		} catch (SQLException e) {
			ZhszLogger.error("添加角色失败", e, this.getClass());
			throw new ServiceException("添加角色失败", e);
		}
	}

	@Override
	@Transactional
	public void deleteRoles(String[] ids) throws ServiceException {
		try {
		    String id = null;
            for (int i = 0; i < ids.length; i++) {
                id = ids[i];
                roleMapper.deleteRole(id);
                menuRoleMapper.deleteMenuRoles(id);
                operatorRoleMapper.deleteOperatorRole(id);
            }
		} catch (SQLException e) {
			ZhszLogger.error("删除角色失败", e, this.getClass());
			throw new ServiceException("删除角色失败", e);
		}

	}

	@Override
	@Transactional
	public void modifyRoleOperator(String id) throws ServiceException {
		try {
			if (id.contains(",")) {
				String[] operatorIds = id.split(",");
				String roleCode = operatorIds[operatorIds.length - 1];
				operatorRoleMapper.deleteOperatorRole(roleCode);
				OperatorRoleEntity operatorRole = new OperatorRoleEntity();
				for (int i = 0; i < operatorIds.length - 1; i++) {
                    String operatorId = operatorIds[i];
                    operatorRole.setOperatorId(operatorId);
                    operatorRole.setRoleCode(roleCode);
                    operatorRole.setId(StringUtils.getUUID());
                    operatorRoleMapper.insertOperatorRole(operatorRole);
				}
			} else {
				operatorRoleMapper.deleteOperatorRole(id);
			}
		} catch (SQLException e) {
			ZhszLogger.error("修改该角色的操作员信息失败", e, this.getClass());
			throw new ServiceException("修改该角色的操作员信息失败", e);
		}
	}

}
