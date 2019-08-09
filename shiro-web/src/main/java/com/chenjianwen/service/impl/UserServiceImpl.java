package com.chenjianwen.service.impl;

import com.chenjianwen.mapper.UserMap;
import com.chenjianwen.model.User;
import com.chenjianwen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMap userMap;

    @Override
    public User findByUsername(String username) {
        return userMap.findByUsername(username);
    }
}
