package com.chenjianwen.controller;

import com.chenjianwen.model.User;
import com.chenjianwen.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: <br>
 * @Date: Created in 2019/8/27 <br>
 * @Author: chenjianwen
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/subLogin",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object login(User user){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        try {
            token.setRememberMe(user.getRememberMe()); //几天免登录
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }

        //判断权限
//        if(subject.hasRole("admin")){
//            return "有admin权限";
//        }

        if(subject.isPermitted("query")){
            return "有add权限";
        }

        return "无add权限";
    }


    /**
     * 注解式权限
     * @return
     */
    @RequiresRoles("admin")
    @RequestMapping(value = "/testAnnotation",method = RequestMethod.GET)
    @ResponseBody
    public String testAnnotation(){
        return "testAnnotation success";
    }
    @RequiresRoles("admin1")
    @RequestMapping(value = "/testAnnotation1",method = RequestMethod.GET)
    @ResponseBody
    public String testAnnotation1(){
        return "testAnnotation1 success";
    }


    @RequestMapping(value = "/testRole",method = RequestMethod.GET)
    @ResponseBody
    public String testRole(){
        return "testRole success";
    }
    @RequestMapping(value = "/testRole1",method = RequestMethod.GET)
    @ResponseBody
    public String testRole1(){
        return "testRole1 success";
    }
    @RequestMapping(value = "/testPerms",method = RequestMethod.GET)
    @ResponseBody
    public String testPerms(){
        return "testPerms success";
    }
    @RequestMapping(value = "/testPerms1",method = RequestMethod.GET)
    @ResponseBody
    public String testPerms1(){
        return "testPerms1 success";
    }
}
