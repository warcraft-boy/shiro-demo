package com.chenjianwen.service;

import com.chenjianwen.model.User;

/**
 * @Description: <br>
 * @Date: Created in 2019/8/27 <br>
 * @Author: chenjianwen
 */
public interface UserService {

    /**
     * 通过用户名查询该用户
     * @param name
     * @return
     */
    User queryUserByUsername(String name);
}
