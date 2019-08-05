package com.chenjianwen.mapper;

import com.chenjianwen.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMap {


    User findByUsername(@Param("username") String username);
}
