package com.chenjianwen.service.impl;

import com.chenjianwen.dao.RoleMapper;
import com.chenjianwen.model.Role;
import com.chenjianwen.model.User;
import com.chenjianwen.model.UserRole;
import com.chenjianwen.service.RoleService;
import com.chenjianwen.service.UserRoleService;
import com.chenjianwen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: <br>
 * @Date: Created in 2019/8/27 <br>
 * @Author: chenjianwen
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryRolesByUsername(String username) {
        User user = userService.queryUserByUsername(username);
        if(user == null){
            return null;
        }
        UserRole userRole = new UserRole();
        userRole.setUid(user.getUid());
        List<UserRole> urList = userRoleService.queryUserRole(userRole);
        if(urList == null || urList.size() == 0){
            return null;
        }
        List<Long> roleIds = urList.stream().map(t -> t.getRid()).collect(Collectors.toList());
        List<Role> roles = roleMapper.selectRoleList(roleIds);
        return roles;
    }
}
