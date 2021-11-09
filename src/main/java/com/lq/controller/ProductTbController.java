package com.lq.controller;


import com.lq.dao.ProductTbMapper;
import com.lq.pojo.ProductTb;
import com.lq.pojo.PurchaseOrderTb;
import com.lq.service.ProductTbService;
import com.lq.utils.DealFile;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
@Controller
@RequestMapping("/productTb")
public class ProductTbController {

    @Resource
    private ProductTbService productTbService;

    @Resource
    private ProductTbMapper productTbMapper;

    @Resource
    private DealFile dealFile;

    //未支付的物品清单
    @GetMapping("allProduct")
    @ResponseBody
    public List<ProductTb> allProduct(String purchaseOrderNumber) {
        return productTbService.allProduct(purchaseOrderNumber);
    }

    //未完成的物品
    @GetMapping("undoneProduct")
    @ResponseBody
    public List<ProductTb> undoneProduct(Integer accountID) {
        return productTbService.undoneProduct(accountID);
    }

    //已完成的物品
    @GetMapping("completedProduct")
    @ResponseBody
    public List<ProductTb> completedProduct(Integer accountID) {
        return productTbService.completedProduct(accountID);
    }

    //查询物流
    @RequestMapping("showApi")
    @ResponseBody
    public String showApi(String logistic,String phone) {
        return productTbService.Logistics(logistic,phone);
    }

    //再买一次
    @PostMapping("BuyItAgainS")
    @ResponseBody
    public int BuyItAgainS(Integer productId,Integer accountId, Integer receiverId, Integer purchaseOrderProductPnID, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice,String remarks,Integer totalPoints,double price,String id) {
        return productTbService.BuyItAgain(productId,accountId, receiverId, purchaseOrderProductPnID, FinishedGoodsQty, purchaseOrderITotalPrice,remarks,totalPoints,price,id);
    }
    //取消订单
    @PostMapping("deleteOrder")
    @ResponseBody
    public void deleteOrder(String purchaseOrderNumber) {
        productTbService.delete(purchaseOrderNumber);
    }

    //需要处理的订单信息
    @GetMapping("ListProduct")
    @ResponseBody
    public List<ProductTb> ListProduct(String purchaseOrderNumber) {
        return productTbService.ListProduct(purchaseOrderNumber);
    }

    //添加快递单号
    @PostMapping("insertLogistic")
    @ResponseBody
    public void insertLogistic(String logistic, Integer productId) {
        productTbService.insertLogistic(logistic, productId);
    }

    //上传客户自定义物品3D文档到服务器
    @PostMapping("DiyUploadS")
    @ResponseBody
    public void DiyUploadS(@RequestParam(value = "file", required = false) MultipartFile multipartFile, Integer productId) throws Exception {
        productTbService.DiyUpload(multipartFile, productId);
    }

    //查看处理完成的订单物品详情
    @GetMapping("allDealProductS")
    @ResponseBody
    public List<ProductTb> allDealProductS (String purchaseOrderNumber) {
        return productTbService.addDealProduct(purchaseOrderNumber);
    }

    //查看处理完成的订单物品
    @GetMapping("allDealS")
    @ResponseBody
    public List<ProductTb> allDealS () {
        return productTbService.allDeal();
    }

    //查看处理完成的不同头部的订单物品
    @GetMapping("touBuAllDealS")
    @ResponseBody
    public List<ProductTb> touBuAllDealS (String touBu) {
        return productTbMapper.touBuDeal(touBu);
    }
    //查看物流中的物品
    @GetMapping("addDealS")
    @ResponseBody
    public List<ProductTb> addDealS () {
        return productTbService.addDeal();
    }

    //查看物流中不同头部的物品
    @GetMapping("touBuAddDealS")
    @ResponseBody
    public List<ProductTb> touBuAddDealS (String touBu,String purchaseOrderProductName) {
        return productTbMapper.touBuAddDeal(touBu,purchaseOrderProductName);
    }

    //物品信息
    @GetMapping("productTbS")
    @ResponseBody
    public ProductTb productTbS (Integer productId) {
        return productTbService.productTb(productId);
    }
    //查看所有订单单个物品的总数
    @GetMapping("numS")
    @ResponseBody
    public String numS (String purchaseOrderProductName){
        return productTbService.num(purchaseOrderProductName);
    }

    //已购买过的物品名称
    @GetMapping("purchaseOrderProductNameS")
    @ResponseBody
    public List<ProductTb> purchaseOrderProductNameS() {
        return productTbService.purchaseOrderProductName();
    }

    //待支付1
    @GetMapping("daiZhiFuS")
    @ResponseBody
    public List<ProductTb> daiZhiFuS(String purchaseOrderNumber) {
        return productTbMapper.daiZhiFu(purchaseOrderNumber);
    }

    //待支付2
    @GetMapping("daiZhiFuS2")
    @ResponseBody
    public List<ProductTb> daiZhiFuS2(String purchaseOrderNumber) {
        return productTbMapper.daiZhiFu2(purchaseOrderNumber);
    }

    //未完成
    @GetMapping("weiWanChengS")
    @ResponseBody
    public List<ProductTb> weiWanChengS(Integer productId) {
        return productTbMapper.weiWanCheng(productId);
    }

    //未完成2
    @GetMapping("weiWanChengS2")
    @ResponseBody
    public List<ProductTb> weiWanChengS2(Integer productId) {
        return productTbMapper.weiWanCheng2(productId);
    }

    //已完成
    @GetMapping("yiWanChengS")
    @ResponseBody
    public List<ProductTb> yiWanChengS(Integer productId) {
        return productTbMapper.yiWanCheng(productId);
    }

    //已完成2
    @GetMapping("yiWanChengS2")
    @ResponseBody
    public List<ProductTb> yiWanChengS2(Integer productId) {
        return productTbMapper.yiWanCheng2(productId);
    }

    //超过15天无法申请售后
    @PostMapping("wuFaShouHouS")
    @ResponseBody
    public void wuFaShouHouS(Integer productId) {
        productTbMapper.shouHou(productId);
    }

    //查看售后的详细信息
    @GetMapping("selectAllS")
    @ResponseBody
    public ProductTb selectAllS (Integer productId) {
        return productTbService.selectAll(productId);
    }

    //上传没有模型的3d文档
    @PostMapping("uploadNoModel")
    @ResponseBody
    public void uploadNoModel(String purchaseOrderNumber,@RequestParam(value = "file", required = false) MultipartFile multipartFile) {
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            productTbMapper.updateAddr(photoPath,purchaseOrderNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //查看上传没有模型的3d文档
    @GetMapping("selectNoModel")
    @ResponseBody
    public String selectNoModel (String purchaseOrderNumber){
        String s = productTbMapper.shopAddr(purchaseOrderNumber);
        return s;
    }
}

