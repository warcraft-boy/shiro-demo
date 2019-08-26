package com.chenjianwen;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Description: <br>jdbcRealm
 * @Date: Created in 2019/8/26 <br>
 * @Author: chenjianwen
 */
public class JdbcRealmTest {

    //创建数据源
    DruidDataSource dataSource = new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

    }

    @Test
    public void testAuthenticate(){

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        //从数据库中查询该用户：
        String sql = "select password from user where user_name = ?";
        jdbcRealm.setAuthenticationQuery(sql);

        //1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //登录请求
        UsernamePasswordToken token = new UsernamePasswordToken("admin","123456");
        subject.login(token);

        System.out.println("isAuthenticate:"+subject.isAuthenticated());

    }
}
