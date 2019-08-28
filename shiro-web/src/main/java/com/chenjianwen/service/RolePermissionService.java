package com.chenjianwen.service;

import com.chenjianwen.model.RolePermission;

import java.util.List;

/**
 * @Description: <br>
 * @Date: Created in 2019/8/27 <br>
 * @Author: chenjianwen
 */
public interface RolePermissionService {

    /**
     * 查询角色权限关系信息
     * @param rolePermission
     * @return
     */
    List<RolePermission> queryRolePermission(RolePermission rolePermission);
}
