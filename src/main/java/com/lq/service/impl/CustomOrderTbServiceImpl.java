package com.lq.service.impl;

import com.lq.pojo.*;
import com.lq.dao.CustomOrderTbMapper;
import com.lq.service.CustomOrderTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.service.FinishedGoodsListTbService;
import com.lq.service.PersonRegisterTbService;
import com.lq.service.ProductTbService;
import com.lq.utils.OrderCodeFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-09-15
 */
@Service
public class CustomOrderTbServiceImpl extends ServiceImpl<CustomOrderTbMapper, CustomOrderTb> implements CustomOrderTbService {
    @Resource
    private PersonRegisterTbService personRegisterTbService;
    @Resource
    private FinishedGoodsListTbService finishedGoodsListTbService;
    @Resource
    private ProductTbService productTbService;
    @Resource
    private CustomOrderTbService customOrderTbService;
    OrderCodeFactory orderCodeFactory = new OrderCodeFactory();
    //生成待确认订单
    @Override
    public int orderTbc(Integer accountId,Integer purchaseOrderProductPnId,Integer totalPoints,String code,String moXing) {
        String customOrder1 = orderCodeFactory.getCustomOrder1(accountId);
        String returnCode = orderCodeFactory.getCustomOrder(accountId);
        CustomOrderTb customOrderTb =new CustomOrderTb();
        ProductTb productTb = new ProductTb();
        PersonRegisterTb personRegisterTb = personRegisterTbService.getById(accountId);
        FinishedGoodsListTb finishedGoodsListTb = finishedGoodsListTbService.getFinished(purchaseOrderProductPnId);
        Date time = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (code.equals("LQSO")) {
            customOrderTb.setPurchaseOrderNumber(returnCode);
        }
        if (code.equals("LQPR")) {
            customOrderTb.setPurchaseOrderNumber(customOrder1);
        }
        customOrderTb.setPurchaseOrderNumber(returnCode);
        customOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
        customOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
        customOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
        customOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
        customOrderTb.setPurchaseOrderSalesPhone("待确认");
        customOrderTb.setOrderHeader(finishedGoodsListTb.getFinishedGoodsName());
        customOrderTb.setAccountId(accountId);
        customOrderTb.setPurchaseOrderIdTotalPrice(0);
        customOrderTb.setPayTime(df2.format(time));
        customOrderTb.setOrderType("待支付");
        customOrderTb.setStatus(0);
        customOrderTb.setDeal("待确认");
        customOrderTb.setTotalPoints(totalPoints);
        customOrderTb.setYuanDing(null);
        customOrderTb.setMoXing(moXing);
        customOrderTbService.save(customOrderTb);
        //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
        productTb.setPurchaseOrderNumber(returnCode);
        productTb.setPurchaseOrderProductPn(finishedGoodsListTb.getPurchaseOrderProductPn());
        productTb.setPurchaseOrderProductPnId(finishedGoodsListTb.getPurchaseOrderProductPnId());
        productTb.setPurchaseOrderProductName(finishedGoodsListTb.getFinishedGoodsName());
        productTb.setPurchaseOrderProductTotalQty(0);
        productTb.setPrinterModelRequestCustomer(finishedGoodsListTb.getPrinterModelRequestCustomer());
        productTb.setFactoryMaterialName(finishedGoodsListTb.getFinishedGoodsTexture());
        productTb.setColorRequestCustomer(finishedGoodsListTb.getFinishedGoodsColor());
        productTb.setProductLengthRequestCustomer(finishedGoodsListTb.getFinishedGoodsLength());
        productTb.setProductWidthRequestCustomer(finishedGoodsListTb.getFinishedGoodsWidth());
        productTb.setProductHeightRequestCustomer(finishedGoodsListTb.getFinishedGoodsHeight());
        productTb.setProductAccRequestCustomer(finishedGoodsListTb.getFinishedGoodsAcc());
        productTb.setProductWeightRequestCustomer(finishedGoodsListTb.getFinishedGoodsWeight());
        productTb.setFactoryMaterialPn(finishedGoodsListTb.getFactoryMaterialPn());
        productTb.setShopAddr(finishedGoodsListTb.getDrawingFilesAddr());
//        productTb.setUploadAddr(uploadTb.getUploadAddr());
        productTb.setDescription(finishedGoodsListTb.getFinishedGoodsDescription());
//        productTb.setDiyComment(uploadTb.getDescription());
        productTb.setHomeAddr(finishedGoodsListTb.getHomePageAddr());
        productTb.setPrice(finishedGoodsListTb.getFinishedGoodsCustomerPriced());
        productTb.setDownloadAddr(null);
        productTb.setLogistic(null);
        productTb.setLogisticName(null);
        productTbService.save(productTb);
//        return productTbMapper.productId();
//        return purchaseOrderTb.getPurchaseOrderNumberId();
        return customOrderTb.getCustomOrderId();
    }
}
