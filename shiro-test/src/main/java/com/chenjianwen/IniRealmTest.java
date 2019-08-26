package com.chenjianwen;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Description: 使用iniRealm
 * @Date: Created in 2019/8/26 <br>
 * @Author: chenjianwen
 */
public class IniRealmTest {

    @Test
    public void testAuthenticate(){

        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //登录请求
        UsernamePasswordToken token = new UsernamePasswordToken("root","123456");
        subject.login(token);

        System.out.println("isAuthenticate:"+subject.isAuthenticated());

        //检查角色
        subject.checkRoles("admin");
        //检查权限
        subject.checkPermissions("user:update");

        //退出
        subject.logout();

        System.out.println("isAuthenticate:"+subject.isAuthenticated());
    }
}
