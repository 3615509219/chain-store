package com.lq.controller;


import com.lq.pojo.ReceiverTb;
import com.lq.service.ReceiverTbService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-01-14
 */
@Controller
@RequestMapping("/receiverTb")
public class ReceiverTbController {
    @Resource
    private ReceiverTbService receiverTbService;

    //获取用户收件人信息
    @GetMapping("getAllReceiver")
    @ResponseBody
    public List<ReceiverTb> getAllReceiver(Integer accountId) {
        return receiverTbService.getAllReceiver(accountId);
    }

    //增加收件人信息
    @PostMapping("addReceiver")
    @ResponseBody
    public void addReceiver(ReceiverTb receiverTb) {
        receiverTbService.addReceiver(receiverTb);
    }

    //删除收件人信息
    @PostMapping("delReceiver")
    @ResponseBody
    public void delReceiver(Integer receiverId) {
        receiverTbService.delReceiver(receiverId);
    }

    //修改收件人信息
    @PostMapping("updateReceiver")
    @ResponseBody
    public void updateReceiver(ReceiverTb receiverTb) {
        receiverTbService.updateReceiver(receiverTb);
    }

    //获取一个地址
    @GetMapping("getReceiver")
    @ResponseBody
    public ReceiverTb getReceiver(Integer accountId,Integer receiverId) {
        return receiverTbService.receiver(accountId,receiverId);
    }

    //修改默认地址
    @PostMapping("updateMrS")
    @ResponseBody
    public void updateMrS (Integer receiverId,Integer accountId){
        receiverTbService.updateMr(receiverId,accountId);
    }

    //获取默认地址
    @GetMapping("mrReceiverS")
    @ResponseBody
    public ReceiverTb mrReceiverS (Integer accountId){
        return receiverTbService.mrReceiver(accountId);
    }
}

