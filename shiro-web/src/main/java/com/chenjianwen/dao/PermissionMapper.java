package com.chenjianwen.dao;

import com.chenjianwen.model.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}