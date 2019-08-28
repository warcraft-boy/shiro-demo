package com.chenjianwen.service.impl;

import com.chenjianwen.dao.RolePermissionMapper;
import com.chenjianwen.model.RolePermission;
import com.chenjianwen.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: <br>
 * @Date: Created in 2019/8/27 <br>
 * @Author: chenjianwen
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermission> queryRolePermission(RolePermission rolePermission) {
        return rolePermissionMapper.selectRolePermission(rolePermission);
    }
}
