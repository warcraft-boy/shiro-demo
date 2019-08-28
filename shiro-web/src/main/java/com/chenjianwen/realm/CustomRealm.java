package com.chenjianwen.realm;

import com.chenjianwen.model.Permission;
import com.chenjianwen.model.Role;
import com.chenjianwen.model.User;
import com.chenjianwen.service.PermissionService;
import com.chenjianwen.service.RoleService;
import com.chenjianwen.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 自定义realm
 * @Date: Created in 2019/8/26 <br>
 * @Author: chenjianwen
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        //获取角色信息：
        List<String> roles = this.getRolesByUsername(username);
        //获取权限信息：
        List<String> permissions = this.getPermissionsByUsername(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 从数据库中获取权限信息：
     * @param username
     * @return
     */
    private List<String> getPermissionsByUsername(String username){
        List<Permission> permissions = permissionService.queryPermissionByUsername(username);
        if(permissions == null || permissions.size() == 0){
            return null;
        }
        return permissions.stream().map(t -> t.getPermissionName()).collect(Collectors.toList());
    }
    /**
     * 从拟数据库中获取角色信息
     * @param username
     * @return
     */
    private List<String> getRolesByUsername(String username){
        List<Role> roles = roleService.queryRolesByUsername(username);
        if(roles == null || roles.size() == 0){
            return null;
        }
        return roles.stream().map(t -> t.getRoleName()).collect(Collectors.toList());
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1、从主体传过来的用户信息中，获取用户名
        String username = (String) token.getPrincipal();
        //2、通过用户名从数据库中获取密码，这里测试就不过数据库，模拟数据库
        String password = this.getPasswordByUsername(username);
        if(StringUtils.isBlank(password)){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,"customRealm");
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("random"));
        return simpleAuthenticationInfo;
    }

    /**
     * 从数据库获取用户密码
     * @param username
     * @return
     */
    private String getPasswordByUsername(String username){
        User user = userService.queryUserByUsername(username);
        if(user == null){
            return null;
        }
        return user.getPassword();
    }

    @Test
    public void testMd5(){
        Md5Hash md5Hash = new Md5Hash("123456","random");
        System.out.println(md5Hash.toString());
    }
}
