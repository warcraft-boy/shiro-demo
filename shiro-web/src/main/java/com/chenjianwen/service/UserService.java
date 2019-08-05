package com.chenjianwen.service;

import com.chenjianwen.model.User;

public interface UserService {

    public User findByUsername(String name);
}
