package com.lq.controller;


import com.lq.pojo.FactoryPoTb;
import com.lq.service.FactoryPoTbService;
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
 * @since 2021-04-15
 */
@Controller
@RequestMapping("/factoryPoTb")
public class FactoryPoTbController {

    @Resource
    private FactoryPoTbService factoryPoTbService;
    //生成订单
    @PostMapping("addFactory")
    @ResponseBody
    public int addFactory(Integer manufactureId, Integer manufactureReceiverId, Integer factoryPrice) {
        return  factoryPoTbService.addFactoryPo(manufactureId, manufactureReceiverId, factoryPrice);
    }
    //待发货
    @GetMapping("ToBeDeliveredS")
    @ResponseBody
    public List<FactoryPoTb> ToBeDeliveredS (Integer manufactureId){
        return factoryPoTbService.ToBeDelivered(manufactureId);
    }
    //已发货
    @GetMapping("DeliveredS")
    @ResponseBody
    public List<FactoryPoTb> DeliveredS (Integer manufactureId){
        return factoryPoTbService.Delivered(manufactureId);
    }
    //待支付
    @GetMapping("ToBePaidS")
    @ResponseBody
    public List<FactoryPoTb> ToBePaidS (Integer manufactureId){
        return factoryPoTbService.ToBePaid(manufactureId);
    }
}

