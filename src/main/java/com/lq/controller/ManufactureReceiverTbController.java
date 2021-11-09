package com.lq.controller;


import com.lq.pojo.ManufactureReceiverTb;
import com.lq.pojo.ReceiverTb;
import com.lq.service.ManufactureReceiverTbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-04-07
 */
@Controller
@RequestMapping("/manufactureReceiverTb")
public class ManufactureReceiverTbController {
    @Resource
    private ManufactureReceiverTbService manufactureReceiverTbService;

    //获取工厂收件人信息
    @GetMapping("getAllManufactureReceiver")
    @ResponseBody
    public List<ManufactureReceiverTb> getAllReceiver(Integer manufactureId) {
        return manufactureReceiverTbService.getAllManufactureReceiver(manufactureId);
    }

    //增加工厂收件人信息
    @PostMapping("addManufactureReceiver")
    @ResponseBody
    public void addReceiver(ManufactureReceiverTb manufactureTb) {
        manufactureReceiverTbService.addManufactureReceiver(manufactureTb);
    }

    //删除工厂收件人信息
    @PostMapping("delManufactureReceiver")
    @ResponseBody
    public void delReceiver(Integer manufactureReceiverId) {
        manufactureReceiverTbService.delManufactureReceiver(manufactureReceiverId);
    }

    //修改工厂收件人信息
    @PostMapping("updateManufactureReceiver")
    @ResponseBody
    public void updateReceiver(ManufactureReceiverTb manufactureReceiverTb) {
        manufactureReceiverTbService.updateManufactureReceiver(manufactureReceiverTb);
    }

    //获取一个地址
    @GetMapping("getReceiver")
    @ResponseBody
    public ManufactureReceiverTb getReceiver(Integer manufactureReceiverId, Integer manufactureId) {
        return manufactureReceiverTbService.oneReceiver(manufactureReceiverId, manufactureId);
    }
}

