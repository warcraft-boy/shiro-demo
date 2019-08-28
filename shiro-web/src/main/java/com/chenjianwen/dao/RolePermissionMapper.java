package com.chenjianwen.dao;

import com.chenjianwen.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    /**
     * 查询角色权限关系信息
     * @param rolePermission
     * @return
     */
    List<RolePermission> selectRolePermission(RolePermission rolePermission);
}