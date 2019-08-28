package com.chenjianwen.service.impl;

import com.chenjianwen.dao.UserRoleMapper;
import com.chenjianwen.model.UserRole;
import com.chenjianwen.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: <br>
 * @Date: Created in 2019/8/27 <br>
 * @Author: chenjianwen
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> queryUserRole(UserRole userRole) {
        return userRoleMapper.selectUserRoleSelective(userRole);
    }
}
