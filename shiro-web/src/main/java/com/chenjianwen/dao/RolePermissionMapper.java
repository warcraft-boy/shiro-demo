package com.chenjianwen.dao;

import com.chenjianwen.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}