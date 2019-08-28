package com.chenjianwen.dao;

import com.chenjianwen.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Long rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 通过角色id集合查询角色信息
     * @param roleIds
     * @return
     */
    List<Role> selectRoleList(List<Long> roleIds);
}