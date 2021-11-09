package com.lq.controller;


import com.lq.dao.OffTheShelfTbMapper;
import com.lq.pojo.OffTheShelfTb;
import com.lq.service.OffTheShelfTbService;
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
 * @since 2021-05-11
 */
@Controller
@RequestMapping("/offTheShelfTb")
public class OffTheShelfTbController {

    @Resource
    private OffTheShelfTbService offTheShelfTbService;

    @Resource
    private OffTheShelfTbMapper offTheShelfTbMapper;

    //申请下架
    @PostMapping("applyForRemovalS")
    @ResponseBody
    public void applyForRemovalS (Integer accountId, Integer purchaseOrderProductPnId,String purchaseOrderProductPn,String finishedGoodsName,String reason){
        offTheShelfTbService.applyForRemoval(accountId, purchaseOrderProductPnId,purchaseOrderProductPn, finishedGoodsName, reason);
    }
    //查询是否在申请中
    @GetMapping("applyS")
    @ResponseBody
    public OffTheShelfTb applyS (Integer purchaseOrderProductPnId){
        return offTheShelfTbMapper.select(purchaseOrderProductPnId);
    }

    //处理下架
    @PostMapping("handleS")
    @ResponseBody
    public void handleS (Integer shelfId){
        offTheShelfTbService.handle(shelfId);
    }

    //获取待处理下架
    @GetMapping("allUnprocessedS")
    @ResponseBody
    public List<OffTheShelfTb> allUnprocessedS (){
        return offTheShelfTbMapper.allUnprocessed();
    }
    //获取已处理下架
    @GetMapping("allProcessedS")
    @ResponseBody
    public List<OffTheShelfTb> allProcessedS (){
        return offTheShelfTbMapper.allProcessed();
    }

    //个人所有下架中
    @GetMapping("xiaJiaZhongS")
    @ResponseBody
    public List<OffTheShelfTb> xiaJiaZhongS (Integer accountId){
        return offTheShelfTbMapper.xiaJiaZhong(accountId);
    }
    //取消下架
    @PostMapping("cancelS")
    @ResponseBody
    public void cancelS (Integer shelfId){
        offTheShelfTbService.removeById(shelfId);
    }
    //填写下架原因
    @PostMapping("updateReasonsForRemovalS")
    @ResponseBody
    public void updateReasonsForRemovalS (String reasonsForRemoval, Integer shelfId){
        offTheShelfTbService.updateReasonsForRemoval(reasonsForRemoval, shelfId);
    }
}

