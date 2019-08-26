package com.chenjianwen;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Description: 自定义Realm测试类
 * @Date: Created in 2019/8/26 <br>
 * @Author: chenjianwen
 */
public class CustomRealmTest {

    @Test
    public void testAuthenticate(){

        CustomRealm customRealm = new CustomRealm();
        //1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //登录请求
        UsernamePasswordToken token = new UsernamePasswordToken("root","123456");
        subject.login(token);

        System.out.println("isAuthenticate:"+subject.isAuthenticated());

        subject.checkRoles("admin","user");
        subject.checkPermissions("user:add","user:delete");

        //退出
        subject.logout();

        System.out.println("isAuthenticate:"+subject.isAuthenticated());
    }

}
