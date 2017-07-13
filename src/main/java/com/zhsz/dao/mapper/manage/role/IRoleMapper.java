package com.zhsz.dao.mapper.manage.role;

import com.zhsz.dao.entity.manage.role.RoleEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IRoleMapper {


    public Long roleCount(Map<String, Object> condition) throws SQLException;

    public List<RoleEntity> searchRoles(Map<String, Object> condition) throws SQLException;

    public RoleEntity getRole(String code) throws SQLException;

    public void updateRole(RoleEntity role) throws SQLException;

    public void insertRole(RoleEntity role) throws SQLException;

    public void deleteRole(String code) throws SQLException;


}
