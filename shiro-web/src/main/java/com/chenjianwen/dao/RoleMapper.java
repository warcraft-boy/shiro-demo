package com.chenjianwen.dao;

import com.chenjianwen.model.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Long rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}