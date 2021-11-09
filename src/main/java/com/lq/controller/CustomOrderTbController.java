package com.lq.controller;


import com.lq.dao.*;
import com.lq.pojo.*;
import com.lq.service.AdvancePaymentTbService;
import com.lq.service.CustomOrderTbService;
import com.lq.service.CustomOrderUploadTbService;
import com.lq.service.ReceiverTbService;
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
 * 前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-09-15
 */
@Controller
@RequestMapping("/customOrderTb")
public class CustomOrderTbController {

    @Resource
    private CustomOrderTbService customOrderTbService;
    @Resource
    private CustomOrderTbMapper customOrderTbMapper;
    @Resource
    private ReceiverTbService receiverTbService;
    @Resource
    private CouponTbMapper couponTbMapper;
    @Resource
    private ProductTbMapper productTbMapper;
    @Resource
    private AdvancePaymentTbMapper advancePaymentTbMapper;
    @Resource
    private CustomOrderUploadTbService customOrderUploadTbService;
    @Resource
    private AdvancePaymentTbService advancePaymentTbService;
    @Resource
    private CustomOrderUploadTbMapper customOrderUploadTbMapper;
    //生成待确认订单
    @PostMapping("orderTbcS")
    @ResponseBody
    public int orderTbcS(Integer accountId, Integer purchaseOrderProductPnId, Integer totalPoints,String code,String moXing) {
        return customOrderTbService.orderTbc(accountId, purchaseOrderProductPnId, totalPoints,code,moXing);
    }

    //变成已确认订单
    @PostMapping("updateTbs")
    @ResponseBody
    public void updateTbs(Integer customOrderId, Integer price, String orderHeader,Integer points) {
        Date time = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        customOrderTbMapper.updateTbs(orderHeader, price, customOrderId,points,df2.format(time));
    }

    //查询所有待确认订单
    @GetMapping("selectAll")
    @ResponseBody
    public List<CustomOrderTb> selectAll() {
        return customOrderTbMapper.selectAll();
    }
    //查询这个客户待确认的订单
    @GetMapping("selectAccount")
    @ResponseBody
    public List<CustomOrderTb> selectAccount(Integer accountId){
        return customOrderTbMapper.selectAccount(accountId);
    }
    //客户填地址和使用优惠卷
    @PostMapping("paymentByAddress")
    @ResponseBody
    public void paymentByAddress (Integer customOrderId ,Integer receiverId,Integer accountId,Integer couponId,Double surplus,Integer qty,String orderNum,Integer price,Double cowMuchCoupon,Integer points){
        ReceiverTb receiverTb = receiverTbService.getById(receiverId);
        customOrderTbMapper.paymentByAddress(receiverTb.getReceiverAddr() + receiverTb.getDetailsAddr(),receiverTb.getReceiverName(),receiverTb.getReceiverPhone(),customOrderId);
        productTbMapper.updateQty(qty,orderNum);
        customOrderTbMapper.updatePrice(price,customOrderId,cowMuchCoupon,points);
        if (surplus != null) {
            customOrderTbMapper.updateCoupon(surplus,couponId,customOrderId);
            CouponTb couponTb = new CouponTb();
            CouponTb coupon1 = couponTbMapper.coupon(couponId);
            couponTb.setAccountId(accountId);
            couponTb.setCouponPrice(surplus);
            couponTb.setBz(0);
            couponTb.setStartTime(coupon1.getStartTime());
            couponTb.setEndTime(coupon1.getEndTime());
            couponTbMapper.insert(couponTb);
        }

    }
    //客户修改定制地址
    @PostMapping("updateReceiver")
    @ResponseBody
    public void updateReceiver (Integer customOrderId ,Integer receiverId){
        ReceiverTb receiverTb = receiverTbService.getById(receiverId);
        customOrderTbMapper.paymentByAddress(receiverTb.getReceiverAddr() + receiverTb.getDetailsAddr(),receiverTb.getReceiverName(),receiverTb.getReceiverPhone(),customOrderId);
    }
    //预付款信息
    @GetMapping("PrepaidHospitalityPayment")
    @ResponseBody
    public List<AdvancePaymentTb> PrepaidHospitalityPayment (String deal){
        return advancePaymentTbMapper.PrepaidHospitalityPayment(deal);
    }
    //预付款单个信息
    @GetMapping("yufukuan")
    @ResponseBody
    public List<AdvancePaymentTb> yufukuan (String advancePaymentOrder){
        return advancePaymentTbMapper.yufukuan(advancePaymentOrder);
    }
    //预付款个人所有订单
    @GetMapping("accountYufukuan")
    @ResponseBody
    public List<AdvancePaymentTb> accountYufukuan (Integer accountId){
        return advancePaymentTbMapper.accountYufukuan(accountId);
    }

    //定制订单详情
    @GetMapping("CustomOrderDetails")
    @ResponseBody
    public List<CustomOrderTb> CustomOrderDetails(String purchaseOrderNumber){
        return customOrderTbMapper.CustomOrderDetails(purchaseOrderNumber);
    }

    //已完成
    @GetMapping("yiWanCheng")
    @ResponseBody
    public List<CustomOrderTb> yiWanCheng (Integer accountId){
        return customOrderTbMapper.yiWanCheng(accountId);
    }

    //未完成
    @GetMapping("weiWanCheng")
    @ResponseBody
    public List<CustomOrderTb> weiWanCheng (Integer accountId){
        return customOrderTbMapper.weiWanCheng(accountId);
    }

    //个人预付款信息
    @GetMapping("PrepaidHospitalityPaymentAccount")
    @ResponseBody
    public List<AdvancePaymentTb> PrepaidHospitalityPaymentAccountId (Integer accountId,String deal){
        return advancePaymentTbMapper.PrepaidHospitalityPaymentAccountId(accountId,deal);
    }
    //订单
    @GetMapping("pendingPaymentConfirmedS")
    @ResponseBody
    public List<CustomOrderTb> pendingPaymentConfirmedS (Integer accountId,String orderType,String deal){
        return customOrderTbMapper.pendingPaymentConfirmed(accountId, orderType, deal);
    }
    //定制取消订单
    @PostMapping("delOrder")
    @ResponseBody
    public void delOrder (Integer customOrderId,Integer advancePaymentId,Integer Id){
        if (Id == 0){
            advancePaymentTbService.removeById(advancePaymentId);
            customOrderUploadTbMapper.delUpload(customOrderId);
            customOrderTbService.removeById(customOrderId);
        }
        if (Id == 1){
            customOrderTbService.removeById(customOrderId);
        }
    }
    //查询所有的预付款
    @GetMapping("allYuFuKuan")
    @ResponseBody
    public List<AdvancePaymentTb> allYuFuKuan (){
        return advancePaymentTbMapper.yufukuan();
    }

    //查询有支付或没支付的预付款
    @GetMapping("dealYuFuKuan")
    @ResponseBody
    public List<AdvancePaymentTb> dealYuFuKuan (String deal){
        return advancePaymentTbMapper.dealYuFuKuan(deal);
    }

//    //有无模型
//    @GetMapping("moXing")
//    @ResponseBody
//    public List<CustomOrderTb> moXing (String moXing){
//        return advancePaymentTbMapper.moXing(moXing);
//    }
    //所有定制订单
    @GetMapping("allDingZhi")
    @ResponseBody
    public List<CustomOrderTb> allDingZhi (){
        return customOrderTbMapper.allDingZhi();
    }
    //带确认有模型的订单
    @GetMapping("daiQueRen")
    @ResponseBody
    public List<CustomOrderTb> daiQueRen (){
        return customOrderTbMapper.daiQueRen();
    }

    //待确认没有模型的订单
    @GetMapping("daiQueRenWu")
    @ResponseBody
    public List<AdvancePaymentTb> daiQueRenWu (){
        return advancePaymentTbMapper.daiQueRenWu();
    }
    //修改为已确认
    @PostMapping("updateYiQueRen")
    @ResponseBody
    public void updateYiQueRen (String purchaseOrderNumber){
        customOrderTbMapper.updateYiQueRen(purchaseOrderNumber);
    }
    //查询所有全部已确认的订单
    @GetMapping("allYiQueRen")
    @ResponseBody
    public List<CustomOrderTb> allYiQueRen(){
        return customOrderTbMapper.allYiQueRen();
    }
    //查询定制上传信息
    @GetMapping("selectUpload")
    @ResponseBody
    public List<CustomOrderUploadTb>  selectUpload (Integer customOrderId){
        return customOrderUploadTbMapper.selectUpload(customOrderId);
    }
    //查询所有已确认待支付的订单
    @GetMapping("ConfirmToBePaid")
    @ResponseBody
    public List<CustomOrderTb> ConfirmToBePaid (){
        return customOrderTbMapper.ConfirmToBePaid();
    }
    //查询所有已确认已支付的订单
    @GetMapping("confirmPaid")
    @ResponseBody
    public List<CustomOrderTb> confirmPaid (){
        return customOrderTbMapper.confirmPaid();
    }
    //查看物品详情
    @GetMapping("viewItemDetails")
    @ResponseBody
    public List<ProductTb> viewItemDetails (String purchaseOrderNumber){
        return customOrderTbMapper.viewItemDetails(purchaseOrderNumber);
    }
    //定制未完成
    @GetMapping("customizationNotCompleted")
    @ResponseBody
    public List<CustomOrderTb> customizationNotCompletedS (){
        return customOrderTbMapper.customizationNotCompleted();
    }

    //定制已完成
    @GetMapping("customizationCompleted")
    @ResponseBody
    public List<CustomOrderTb> customizationCompletedS (){
        return customOrderTbMapper.customizationCompleted();
    }


}

