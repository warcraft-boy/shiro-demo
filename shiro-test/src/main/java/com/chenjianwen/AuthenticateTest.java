package com.chenjianwen;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @Description: shiro认证的简单过程
 * @Date: Created in 2019/8/26 <br>
 * @Author: chenjianwen
 */
public class AuthenticateTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
    @Before
    public void addUser(){
        //构建realm
        simpleAccountRealm.addAccount("root","123456");
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

        //退出
        subject.logout();

        System.out.println("isAuthenticate:"+subject.isAuthenticated());
    }
}
