package com.lq.controller;


import com.lq.dao.PurchaseOrderTbMapper;
import com.lq.pojo.CustomOrderTb;
import com.lq.pojo.PurchaseOrderTb;
import com.lq.service.PurchaseOrderTbService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
@Controller
@RequestMapping("/purchaseOrderTb")
public class PurchaseOrderTbController {
    @Resource
    private PurchaseOrderTbService purchaseOrderTbService;

    @Resource
    private PurchaseOrderTbMapper purchaseOrderTbMapper;

    //生成订单
    @PostMapping("addPurchaseOrder")
    @ResponseBody
    public int addPurchaseOrder(Integer accountId, Integer receiverId, Integer purchaseOrderITotalPrice, String remarks, Integer totalPoints,String id,int couponId,Double coupon,Double surplus) {
      return  purchaseOrderTbService.addPurchaseOrder(accountId, receiverId, purchaseOrderITotalPrice,remarks,totalPoints,id,couponId,coupon,surplus);
    }

    //未支付的订单
    @GetMapping("UnpaidOrders")
    @ResponseBody
    public List<PurchaseOrderTb> UnpaidOrders(Integer accountId) {
        return purchaseOrderTbService.Unpaid(accountId);
    }

    //立即购买
    @PostMapping("BuyNow")
    @ResponseBody
    public Map<String, Integer> BuyNow(Integer accountId, Integer receiverId, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice,String remarks,Integer totalPoints,String id,int couponId,Double coupon,Double surplus) {
       return purchaseOrderTbService.addPurchase(accountId, receiverId, purchaseOrderProductPnID,finishedGoodsListSubId, FinishedGoodsQty, purchaseOrderITotalPrice,remarks,totalPoints,id,couponId,coupon,surplus);
    }
    //取消订单
    @PostMapping("delPurchase")
    @ResponseBody
    public void delPurchase(String purchaseOrderNumber) {
         purchaseOrderTbService.delPurchase(purchaseOrderNumber);
    }

    //获取支付完成待处理的订单
    @GetMapping("ListPurchaseS")
    @ResponseBody
    public List<PurchaseOrderTb> ListPurchaseS () {
        return purchaseOrderTbService.ListPurchase();
    }

    //获取支付完成待处理不同头部的订单
    @GetMapping("touBuS")
    @ResponseBody
    public List<PurchaseOrderTb> touBuS (String touBu) {
        return purchaseOrderTbMapper.touBu(touBu);
    }

    //处理完成
    @PostMapping("updatePurchaseS")
    @ResponseBody
    public void updatePurchaseS(String purchaseOrderNumber) {
        purchaseOrderTbService.updatePurchase(purchaseOrderNumber);
    }

    //修改未支付的地址
    @PostMapping("updateReceiverS")
    @ResponseBody
    public void updateReceiverS(String purchaseOrderNumber, Integer receiverId) {
        purchaseOrderTbService.updateReceiver(purchaseOrderNumber, receiverId);
    }

    //立即购买2
    @PostMapping("addPurchase2")
    @ResponseBody
    public Map<String, Integer> addPurchase2(Integer accountId, Integer receiverId, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice,String remarks,String diyRemarks,Integer totalPoints,String id,int couponId,Double coupon,Double surplus) {
        return purchaseOrderTbService.addPurchase2(accountId, receiverId, purchaseOrderProductPnID,finishedGoodsListSubId, FinishedGoodsQty, purchaseOrderITotalPrice,remarks,diyRemarks,totalPoints,id,couponId,coupon,surplus);
    }

    //立即购买0
    @PostMapping("addPurchase0")
    @ResponseBody
    public Map<String, Integer> addPurchase0(Integer accountId, Integer receiverId, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice,String remarks,Integer totalPoints,String id,int couponId,Double coupon,Double surplus) {
        return purchaseOrderTbService.addPurchase0(accountId, receiverId, purchaseOrderProductPnID,finishedGoodsListSubId, FinishedGoodsQty, purchaseOrderITotalPrice,remarks,totalPoints,id,couponId,coupon,surplus);
    }

    //获取所有推荐人Id
    @GetMapping("IDS")
    @ResponseBody
    public List<PurchaseOrderTb> IDS(){
        return purchaseOrderTbMapper.id();
    }

    //有推荐人ID的订单
    @GetMapping("AllYesIDS")
    @ResponseBody
    public List<PurchaseOrderTb> AllYesIDS (){
        return purchaseOrderTbMapper.yesId();
    }

    //此推荐人的所有订单
    @GetMapping("AllIDS")
    @ResponseBody
    public List<PurchaseOrderTb> AllIDS (String id){
        return purchaseOrderTbMapper.allId(id);
    }

    //此推荐人的待处理订单
    @GetMapping("daiChuLiS")
    @ResponseBody
    public List<PurchaseOrderTb> daiChuLiS (String id){
        return purchaseOrderTbMapper.daiChuLi(id);
    }

    //此推荐人的已处理订单
    @GetMapping("yiChuLiS")
    @ResponseBody
    public List<PurchaseOrderTb> yiChuLiS (String id){
        return purchaseOrderTbMapper.yiChuLi(id);
    }

    //处理结果
    @GetMapping("updateChuLiS")
    @ResponseBody
    public void updateChuLiS (Integer id){
        purchaseOrderTbMapper.updateChuLi(id);
    }

    //推荐人处理订单
    @GetMapping("chuLiS")
    @ResponseBody
    public List<PurchaseOrderTb> chuLiS (String chuLi){
        return purchaseOrderTbMapper.chuLi(chuLi);
    }

    //为他人购买1
    @PostMapping("purchasingAgentS1")
    @ResponseBody
    public Map<String, Integer> purchasingAgentS1(Integer accountId,String yuanDing, String name, String tel, String addr, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice,String remarks,Integer totalPoints,String id) {
        return purchaseOrderTbService.purchasingAgent1(accountId,yuanDing, name,tel,addr, purchaseOrderProductPnID,finishedGoodsListSubId, FinishedGoodsQty, purchaseOrderITotalPrice,remarks,totalPoints,id);
    }

    //为他人购买2
    @PostMapping("purchasingAgentS2")
    @ResponseBody
    public Map<String, Integer> purchasingAgentS2(Integer accountId,  String name, String tel, String addr, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice,String remarks,String diyRemarks,Integer totalPoints,String id) {
        return purchaseOrderTbService.purchasingAgent2(accountId, name,tel,addr, purchaseOrderProductPnID,finishedGoodsListSubId, FinishedGoodsQty, purchaseOrderITotalPrice,remarks,diyRemarks,totalPoints,id);
    }

    //为他人购买0
    @PostMapping("purchasingAgentS0")
    @ResponseBody
    public Map<String, Integer> purchasingAgentS0(Integer accountId, String name, String tel, String addr, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice,String remarks,Integer totalPoints,String id) {
        return purchaseOrderTbService.purchasingAgent0(accountId, name,tel,addr, purchaseOrderProductPnID,finishedGoodsListSubId, FinishedGoodsQty, purchaseOrderITotalPrice,remarks,totalPoints,id);
    }
    //查看有nfc的订单号
    @GetMapping("selectPurchaseOrderNumberS")
    @ResponseBody
    public List<PurchaseOrderTb> selectPurchaseOrderNumberS(){
        return purchaseOrderTbMapper.selectPurchaseOrderNumber();
    }

    //按时间段查询订单
    @GetMapping("dateOrder")
    @ResponseBody
    public List<PurchaseOrderTb> dateOrder (String congDate,String daoDate,String orderType){
        return purchaseOrderTbMapper.dateOrder(congDate, daoDate, orderType);
    }
    //按时间段查询定制订单
    @GetMapping("CustomDateOrder")
    @ResponseBody
    public List<CustomOrderTb> CustomDateOrder (String congDate, String daoDate, String orderType){
        return purchaseOrderTbMapper.CustomDateOrder(congDate, daoDate, orderType);
    }

    //按时间段查询待确认或已确认定制订单
    @GetMapping("daiQueRenCustomDateOrder")
    @ResponseBody
    public List<CustomOrderTb> daiQueRenCustomDateOrder (String congDate, String daoDate, String deal){
        return purchaseOrderTbMapper.daiQueRenCustomDateOrder(congDate, daoDate, deal);
    }
}

