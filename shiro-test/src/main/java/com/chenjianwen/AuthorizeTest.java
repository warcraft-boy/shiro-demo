package com.chenjianwen;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @Description: 授权
 * @Date: Created in 2019/8/26 <br>
 * @Author: chenjianwen
 */
public class AuthorizeTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
    @Before
    public void addUser(){
        //构建realm,并赋予该用户角色
        simpleAccountRealm.addAccount("root","123456","admin","user");
    }

    @Test
    public void testAuthenticate(){

        //1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //登录请求
        UsernamePasswordToken token = new UsernamePasswordToken("root","123456");
        subject.login(token);

        System.out.println("isAuthenticate:"+subject.isAuthenticated());

        //检查该用户是否具有该角色
        subject.checkRoles("user","admin");

        //退出
        subject.logout();

        System.out.println("isAuthenticate:"+subject.isAuthenticated());
    }
}
