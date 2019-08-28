package com.chenjianwen.dao;

import com.chenjianwen.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    /**
     * 根据用户id或角色id查询用户角色关系
     * @param userRole
     * @return
     */
    List<UserRole> selectUserRoleSelective(UserRole userRole);
}