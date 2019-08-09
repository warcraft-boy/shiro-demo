package com.chenjianwen.mapper;

import com.chenjianwen.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMap {

    /**
     * 根据用户名称查询用户信息
     * @param username 用户名
     * @return
     */
    User findByUsername(@Param("username") String username);
}
