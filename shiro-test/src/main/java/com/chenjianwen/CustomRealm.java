package com.chenjianwen;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 自定义realm
 * @Date: Created in 2019/8/26 <br>
 * @Author: chenjianwen
 */
public class CustomRealm extends AuthorizingRealm {

    Map<String,String> userMap = new HashMap<>();
    {
        userMap.put("root","123456");
        super.setName("customerRealm");
    }


    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        //获取角色信息：
        Set<String> roles = this.getRolesByUsername(username);
        //获取权限信息：
        Set<String> permissions = this.getPermissionsByUsername(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 模拟数据库，获取权限信息：
     * @param username
     * @return
     */
    private Set<String> getPermissionsByUsername(String username){
        Set<String> sets = new HashSet<>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }
    /**
     * 模拟数据库，获取角色信息
     * @param username
     * @return
     */
    private Set<String> getRolesByUsername(String username){
        Set<String> sets = new HashSet<>();
        sets.add("admin");
        sets.add("user");
        return sets;
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
        String password = getPasswordByUsernama(username);
        if(StringUtils.isBlank(password)){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("root",password,"customRealm");
        return simpleAuthenticationInfo;
    }

    /**
     * 模拟数据库用
     * @param username
     * @return
     */
    private String getPasswordByUsernama(String username){
        return userMap.get(username);
    }
}
