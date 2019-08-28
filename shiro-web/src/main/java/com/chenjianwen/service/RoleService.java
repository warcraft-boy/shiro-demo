package com.chenjianwen.service;

import com.chenjianwen.model.Role;

import java.util.List;

/**
 * @Description: <br>
 * @Date: Created in 2019/8/27 <br>
 * @Author: chenjianwen
 */
public interface RoleService {

    /**
     * 根据用户名称查询角色信息
     * @param username
     * @return
     */
    List<Role> queryRolesByUsername(String username);
}
