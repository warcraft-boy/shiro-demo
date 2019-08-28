package com.chenjianwen.service.impl;

import com.chenjianwen.dao.PermissionMapper;
import com.chenjianwen.model.Permission;
import com.chenjianwen.model.Role;
import com.chenjianwen.model.RolePermission;
import com.chenjianwen.service.PermissionService;
import com.chenjianwen.service.RolePermissionService;
import com.chenjianwen.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: <br>
 * @Date: Created in 2019/8/27 <br>
 * @Author: chenjianwen
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<Permission> queryPermissionByUsername(String username) {
        List<Permission> permissionList = new ArrayList<>();
        List<Role> roles = roleService.queryRolesByUsername(username);
        if(roles != null && roles.size() > 0){
            List<Long> roleIds = roles.stream().map(t -> t.getRid()).collect(Collectors.toList());
            for(Long roleId : roleIds){
                RolePermission rp = new RolePermission();
                rp.setRid(roleId);
                List<RolePermission> rpList = rolePermissionService.queryRolePermission(rp);
                if(rpList != null && rpList.size() > 0){
                    List<Long> pids = rpList.stream().map(t -> t.getPid()).collect(Collectors.toList());
                    for(Long pid : pids){
                        Permission permission = permissionMapper.selectByPrimaryKey(pid);
                        permissionList.add(permission);
                    }
                }
            }
        }
        return permissionList;
    }
}
