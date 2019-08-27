package com.chenjianwen.controller;

import com.chenjianwen.model.User;
import com.chenjianwen.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        return "登陆成功";
    }

    @RequestMapping(value = "/queryUser",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object getUser(){
        String username = "admin";
        User user = userService.queryUserByUsername(username);
        return user;
    }
}
