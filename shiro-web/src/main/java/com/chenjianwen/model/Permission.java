package com.chenjianwen.model;

public class Permission {

    /**
     * 权限id
     */
    private Integer pid;
    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限资源
     */
    private String url;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
