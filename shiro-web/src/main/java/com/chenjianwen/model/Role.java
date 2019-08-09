package com.chenjianwen.model;

import java.util.HashSet;
import java.util.Set;

public class Role {

    /**
     * 角色id
     */
    private Integer rid;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 权限集
     */
    private Set<Permission> permissionSet = new HashSet<>();
    /**
     * 用户集
     */
    private Set<User> userSet = new HashSet<>();

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
