package com.chenjianwen.service;

import com.chenjianwen.model.UserRole;

import java.util.List;

/**
 * @Description: <br>
 * @Date: Created in 2019/8/27 <br>
 * @Author: chenjianwen
 */
public interface UserRoleService {

    /**
     * 根据用户id或角色id查询用户角色关系
     * @param userRole
     * @return
     */
    List<UserRole> queryUserRole(UserRole userRole);
}
