package com.lq.controller;


import com.lq.pojo.ManufactureReceiverTb;
import com.lq.pojo.ManufactureRegisterTb;
import com.lq.pojo.PersonRegisterTb;
import com.lq.service.ManufactureRegisterTbService;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @since 2021-01-13
 */
@Controller
@RequestMapping("/manufactureRegisterTb")
public class ManufactureRegisterTbController {

    @Resource
    private ManufactureRegisterTbService manufactureRegisterTbService;

    //完善工厂信息
    @PostMapping("addManufacture")
    @ResponseBody
    public void addManufacture(ManufactureRegisterTb manufactureRegisterTb) {
        manufactureRegisterTbService.addManufacture(manufactureRegisterTb);
    }

    //修改工厂信息
    @RequestMapping("updateManufacture")
    @ResponseBody
    public void updateManufacture(ManufactureRegisterTb manufactureRegisterTb) {
        manufactureRegisterTbService.updateManufactureRegister(manufactureRegisterTb);
    }

    //验证用户OpenID
    @GetMapping("getManufactureOpenId")
    @ResponseBody
    public ManufactureRegisterTb getManufactureOpenId(String manufactureOpenId) {
        return manufactureRegisterTbService.getManufactureOpenId(manufactureOpenId);
    }

}

