package com.lq.service;

import com.lq.pojo.PurchaseOrderTb;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lq.pojo.ReceiverTb;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
public interface PurchaseOrderTbService extends IService<PurchaseOrderTb> {
    //生成订单
    int addPurchaseOrder(Integer accountId, Integer receiverId,Integer purchaseOrderITotalPrice,String remarks,Integer totalPoints,String id,int couponId,Double coupon,Double surplus);

    //未支付的订单
    List<PurchaseOrderTb> Unpaid(Integer accountId);

    //立即购买1
    Map<String, Integer> addPurchase(Integer accountId, Integer receiverId, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice, String remarks, Integer totalPoints,String id,int couponId,Double coupon,Double surplus);

    //取消订单
    void delPurchase(String purchaseOrderNumber);

    //获取支付完成待处理的订单
    List<PurchaseOrderTb> ListPurchase();

    //处理完成
    void updatePurchase(String purchaseOrderNumber);

    //未支付订单修改地址
    void updateReceiver(String purchaseOrderNumber,Integer receiverId);

    //立即购买2
    Map<String, Integer> addPurchase2(Integer accountId, Integer receiverId,Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId,Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice,String remarks,String diyRemarks,Integer totalPoints,String id,int couponId,Double coupon,Double surplus);

    //立即购买0
    Map<String, Integer> addPurchase0(Integer accountId, Integer receiverId,Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId,Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice,String remarks,Integer totalPoints,String id,int couponId,Double coupon,Double surplus);


    //为他人购买1
    Map<String, Integer> purchasingAgent1(Integer accountId,String yuanDing, String name,String tel,String addr, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice, String remarks, Integer totalPoints,String id);

    //为他人购买2
    Map<String, Integer> purchasingAgent2(Integer accountId, String name,String tel,String addr,Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId,Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice,String remarks,String diyRemarks,Integer totalPoints,String id);

    //为他人购买0
    Map<String, Integer> purchasingAgent0(Integer accountId, String name,String tel,String addr,Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId,Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice,String remarks,Integer totalPoints,String id);

//    //查看有nfc的订单号
//    Map<String,String> selectPurchaseOrderNumber();
}
