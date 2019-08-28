package com.chenjianwen.service;

import com.chenjianwen.model.Permission;

import java.util.List;

/**
 * @Description: <br>
 * @Date: Created in 2019/8/27 <br>
 * @Author: chenjianwen
 */
public interface PermissionService {

    /**
     * 根据用户名查询权限信息
     * @param username
     * @return
     */
    List<Permission> queryPermissionByUsername(String username);
}
