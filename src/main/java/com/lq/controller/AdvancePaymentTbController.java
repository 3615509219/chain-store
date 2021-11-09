package com.lq.controller;


import com.lq.dao.AdvancePaymentTbMapper;
import com.lq.dao.CustomOrderTbMapper;
import com.lq.pojo.AdvancePaymentTb;
import com.lq.pojo.CustomOrderTb;
import com.lq.pojo.FinishedGoodsListTb;
import com.lq.service.AdvancePaymentTbService;
import com.lq.service.CustomOrderTbService;
import com.lq.service.FinishedGoodsListTbService;
import com.lq.utils.OrderCodeFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-09-18
 */
@Controller
@RequestMapping("/advancePaymentTb")
public class AdvancePaymentTbController {

    @Resource
    private FinishedGoodsListTbService finishedGoodsListTbService;
    @Resource
    private CustomOrderTbService customOrderTbService;
    @Resource
    private AdvancePaymentTbService advancePaymentTbService;
    @Resource
    private AdvancePaymentTbMapper advancePaymentTbMapper;

    @Resource
    private CustomOrderTbMapper customOrderTbMapper;
    OrderCodeFactory orderCodeFactory = new OrderCodeFactory();
    //生成预付款订单
    @PostMapping("advancePaymentS")
    @ResponseBody
    public int advancePaymentS (Integer accountId,Integer purchaseOrderProductPnId,Integer customOrderId){
        Date time = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FinishedGoodsListTb byId = finishedGoodsListTbService.getById(purchaseOrderProductPnId);
        CustomOrderTb byId1 = customOrderTbMapper.selectP(customOrderId);
        double d = byId.getFinishedGoodsCustomerPriced()*100;
        String advancePayment = orderCodeFactory.getAdvancePayment(accountId);
        AdvancePaymentTb advancePaymentTb = new AdvancePaymentTb();
        advancePaymentTb.setAccountId(accountId);
        advancePaymentTb.setOrderHeader("预付款");
        advancePaymentTb.setAdvancePaymentOrder(advancePayment);
        advancePaymentTb.setPrice(new Double(d).intValue());
        advancePaymentTb.setPurchaseOrderNumber(byId1.getPurchaseOrderNumber());
        advancePaymentTb.setDeal("待支付");
        advancePaymentTb.setAdvancePaymentDate(df2.format(time));
        advancePaymentTbMapper.insert(advancePaymentTb);
        return advancePaymentTb.getAdvancePaymentId();
    }

    //查看预付款信息
    @GetMapping("a")
    @ResponseBody
    public List<AdvancePaymentTb> a (String purchaseOrderNumber){
        return advancePaymentTbMapper.a(purchaseOrderNumber);
    }

    @GetMapping("pppp")
    @ResponseBody
    public AdvancePaymentTb advancePaymentTb (Integer Id){
        return advancePaymentTbMapper.pppp(Id);
    }

//    //查看所有预付款
//    @GetMapping("advancePaymentOneS")
//    @ResponseBody
//    public void advancePaymentOneS (){
//        advancePaymentTbService.list();
//    }
//    //查看用户所有的预付款
//    @GetMapping("advancePaymentAllaccountS")
//    @ResponseBody
//    public void advancePaymentAllaccountS (){
//
//    }
}

