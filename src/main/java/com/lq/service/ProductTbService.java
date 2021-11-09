package com.lq.service;

import com.lq.pojo.ProductTb;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lq.pojo.PurchaseOrderTb;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
public interface ProductTbService extends IService<ProductTb> {

    //未支付的物品清单
    List<ProductTb> allProduct (String purchaseOrderNumber);

    //未完成的物品
    List<ProductTb> undoneProduct (Integer accountID);

    //已完成的物品
    List<ProductTb> completedProduct(Integer accountID);

    //查询物流
    String Logistics (String logistic,String phone);

    //在买一个
    int BuyItAgain(Integer productId,Integer accountId,Integer receiverId, Integer purchaseOrderProductPnID, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice, String remarks,Integer totalPoints,double price,String id) ;

    //取消订单
    void delete(String purchaseOrderNumber);

    //订单信息
    List<ProductTb> ListProduct(String purchaseOrderNumber);

    //物品信息
    ProductTb productTb (Integer productId);

    //添加快递单号
    void insertLogistic (String logistic , Integer productId);

    //上传客户自定义物品3D文档到服务器
    void DiyUpload(@RequestParam(value = "file",required = false) MultipartFile multipartFile,Integer productId);

    //查看处理完成订单物品详情
    List<ProductTb> addDealProduct (String purchaseOrderNumber);

    //查看处理完成的订单
    List<ProductTb> allDeal ();

    //查看物流中的快递
    List<ProductTb> addDeal();

    //查看所有订单单个物品的总数
    String num (String purchaseOrderProductName);

    //已购买的物品名称
    List<ProductTb> purchaseOrderProductName();

    //售后信息
    ProductTb selectAll (Integer productId);
}
