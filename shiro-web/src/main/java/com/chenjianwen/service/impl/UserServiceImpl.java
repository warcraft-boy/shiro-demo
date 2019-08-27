package com.chenjianwen.service.impl;

import com.chenjianwen.dao.UserMapper;
import com.chenjianwen.model.User;
import com.chenjianwen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: <br>
 * @Date: Created in 2019/8/27 <br>
 * @Author: chenjianwen
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByUsername(String name) {
        return userMapper.selectUserByUsername(name);
    }
}
