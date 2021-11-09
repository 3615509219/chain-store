package com.lq.controller;


import com.lq.dao.UserTbMapper;
import com.lq.pojo.UserTb;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-04-22
 */
@Controller
@RequestMapping("/userTb")
public class UserTbController {

    @Resource
    private UserTbMapper userTbMapper;

    @PostMapping("loginS")
    @ResponseBody
    public String loginS (String userName,String userPassword){
        UserTb login = userTbMapper.login(userName, userPassword);
        if (login == null){
            return "账号或密码不正确";
        }else {
            return "登录成功";
        }
    }

}

