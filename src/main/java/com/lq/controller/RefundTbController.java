package com.lq.controller;


import com.lq.pojo.ReceiverTb;
import com.lq.pojo.RefundTb;
import com.lq.service.RefundTbService;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-06-25
 */
@Controller
@RequestMapping("/refundTb")
public class RefundTbController {

    @Resource
    private RefundTbService refundTbService;
    //申请售后
    @PostMapping("shouHouS")
    @ResponseBody
    public int shouHouS(Integer accountId, String purchaseOrderNumber, Integer productId, String purchaseOrderProductPn, String purchaseOrderProductName, Integer jiFen, Integer referencesId, String reasonsForApplication, String applicationCategory, String applicationReasonDescription) {
       return refundTbService.shouHou(accountId, purchaseOrderNumber, productId, purchaseOrderProductPn, purchaseOrderProductName, jiFen, referencesId, reasonsForApplication, applicationCategory, applicationReasonDescription);
    }
    //获取全部退款的物品
    @GetMapping("allRefundS")
    @ResponseBody
    public List<RefundTb> allRefundS (){
        return refundTbService.list();
    }
    //售后申请中
    @GetMapping("applicationInProgressS")
    @ResponseBody
    public List<RefundTb> applicationInProgressS (){
        return refundTbService.applicationInProgress();
    }

    //售后已完成
    @GetMapping("completedS")
    @ResponseBody
    public List<RefundTb> completedS (){
        return refundTbService.completed();
    }

    //修改售后状态为已完成
    @PostMapping("updateStatusS")
    @ResponseBody
    public void updateStatusS (Integer refundId,String bz){
        refundTbService.updateStatus(refundId,bz);
    }

    //查询此物品是否在售后
    @GetMapping("selectS")
    @ResponseBody
    public RefundTb selectS (Integer productId){
        return refundTbService.select(productId);
    }

}

