package com.lq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.dao.*;
import com.lq.pojo.*;
import com.lq.service.*;
import com.lq.utils.DealFile;
import com.lq.utils.OrderCodeFactory;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
@Service
public class PurchaseOrderTbServiceImpl extends ServiceImpl<PurchaseOrderTbMapper, PurchaseOrderTb> implements PurchaseOrderTbService {

    @Resource
    private PurchaseOrderTbMapper purchaseOrderTbMapper;
    @Resource
    private PurchaseOrderTbService purchaseOrderTbService;
    @Resource
    private PersonRegisterTbService personRegisterTbService;
    @Resource
    private ReceiverTbService receiverTbService;
    @Resource
    private CustomerCartTbMapper customerCartTbMapper;
    @Resource
    private FinishedGoodsListTbService finishedGoodsListTbService;
    @Resource
    private ProductTbService productTbService;
    @Resource
    private CustomerCartTbService customerCartTbService;
    @Resource
    private CartImgTbMapper cartImgTbMapper;
    @Resource
    private ProductImgTbService productImgTbService;
    @Resource
    private ProductTbMapper productTbMapper;
    @Resource
    private CouponTbMapper couponTbMapper;
    @Resource
    private FinishedGoodsListSubTbMapper finishedGoodsListSubTbMapper;

    OrderCodeFactory orderCodeFactory = new OrderCodeFactory();

    //生成订单
    @Override
    @Transactional
    public int addPurchaseOrder(Integer accountId, Integer receiverId, Integer purchaseOrderITotalPrice, String remarks, Integer totalPoints, String id, int couponId, Double coupon, Double surplus) {
        String returnCode = orderCodeFactory.getReturnCode(accountId);
        String orderCode = orderCodeFactory.getOrderCode(accountId);
        PurchaseOrderTb purchaseOrderTb = new PurchaseOrderTb();
        ProductTb productTb = new ProductTb();
        PersonRegisterTb personRegisterTb = personRegisterTbService.getById(accountId);
        ReceiverTb receiverTb = receiverTbService.getById(receiverId);
        List<CustomerCartTb> accountCart = customerCartTbMapper.getAccountCart(accountId);
        Date time = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Lqi = personRegisterTb.getAccountCode();
        if (Lqi.equals("LQI")) {
            purchaseOrderTb.setPurchaseOrderNumber(orderCode);
        }
        if (Lqi.equals("LQC")) {
            purchaseOrderTb.setPurchaseOrderNumber(returnCode);
        }
        purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
        purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
        purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
        purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(receiverTb.getReceiverAddr() + receiverTb.getDetailsAddr());
        purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(receiverTb.getReceiverName());
        purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(receiverTb.getReceiverPhone());
        purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
        purchaseOrderTb.setPurchaseOrderSalesNames(id);
        purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
        List<String> list = Lists.newArrayList();
        for (CustomerCartTb c : accountCart) {
            list.add(c.getFinishedGoodsName());
        }
        purchaseOrderTb.setOrderHeader(String.join(",", list));
        purchaseOrderTb.setAccountId(accountId);
        purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
        purchaseOrderTb.setPayTime(df2.format(time));
        purchaseOrderTb.setOrderType("待支付");
        purchaseOrderTb.setRemarks(remarks);
        purchaseOrderTb.setStatus(0);
        purchaseOrderTb.setDeal("待处理");
        purchaseOrderTb.setTotalPoints(totalPoints);
        purchaseOrderTb.setYuanDing(null);
        purchaseOrderTb.setCouponId(couponId);
        purchaseOrderTb.setCoupon(coupon);
        purchaseOrderTbService.save(purchaseOrderTb);
        if (surplus != null) {
            CouponTb couponTb = new CouponTb();
            CouponTb coupon1 = couponTbMapper.coupon(couponId);
            couponTb.setAccountId(accountId);
            couponTb.setCouponPrice(surplus);
            couponTb.setBz(0);
            couponTb.setStartTime(coupon1.getStartTime());
            couponTb.setEndTime(coupon1.getEndTime());
            couponTbMapper.insert(couponTb);
        }
        //添加到物品清单
        for (CustomerCartTb customerCartTb : accountCart) {
            if (customerCartTb.getFinishedGoodsListSubId() == 0){
                FinishedGoodsListTb finishedGoodsListTb = finishedGoodsListTbService.getFinished(customerCartTb.getPurchaseOrderProductPnId());

                if (Lqi.equals("LQI")) {
                    productTb.setPurchaseOrderNumber(orderCode);
                }
                if (Lqi.equals("LQC")) {
                    productTb.setPurchaseOrderNumber(returnCode);
                }
                productTb.setPurchaseOrderProductPn(customerCartTb.getPurchaseOrderProductPn());
                productTb.setPurchaseOrderProductPnId(customerCartTb.getPurchaseOrderProductPnId());
                productTb.setPurchaseOrderProductName(customerCartTb.getFinishedGoodsName());
                productTb.setPurchaseOrderProductTotalQty(customerCartTb.getFinishedGoodsQty());
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
//            productTb.setUploadAddr(customerCartTb.getUploadAddr());
                productTb.setHomeAddr(customerCartTb.getImgAddr());
                productTb.setDescription(finishedGoodsListTb.getFinishedGoodsDescription());
//            productTb.setDiyComment(customerCartTb.getComment());
                productTb.setPrice(customerCartTb.getUnitPrice());
                productTb.setDownloadAddr(null);
                productTb.setLogistic(null);
                productTb.setLogisticName(null);
                productTb.setCustomerCartID(customerCartTb.getCustomerCartId());
                productTbService.save(productTb);
            }
            if (customerCartTb.getFinishedGoodsListSubId() != 0){
                FinishedGoodsListTb finishedGoodsListTb = finishedGoodsListTbService.getFinished(customerCartTb.getPurchaseOrderProductPnId());
                FinishedGoodsListSubTb f = finishedGoodsListSubTbMapper.f(customerCartTb.getFinishedGoodsListSubId());
                if (Lqi.equals("LQI")) {
                    productTb.setPurchaseOrderNumber(orderCode);
                }
                if (Lqi.equals("LQC")) {
                    productTb.setPurchaseOrderNumber(returnCode);
                }
                productTb.setPurchaseOrderProductPn(customerCartTb.getPurchaseOrderProductPn());
                productTb.setPurchaseOrderProductPnId(customerCartTb.getPurchaseOrderProductPnId());
                productTb.setPurchaseOrderProductName(customerCartTb.getFinishedGoodsName());
                productTb.setPurchaseOrderProductTotalQty(customerCartTb.getFinishedGoodsQty());
                productTb.setPrinterModelRequestCustomer(finishedGoodsListTb.getPrinterModelRequestCustomer());
                productTb.setFactoryMaterialName(f.getFinishedGoodsTexture());
                productTb.setColorRequestCustomer(f.getFinishedGoodsColor());
                productTb.setProductLengthRequestCustomer(f.getFinishedGoodsLength());
                productTb.setProductWidthRequestCustomer(f.getFinishedGoodsWidth());
                productTb.setProductHeightRequestCustomer(f.getFinishedGoodsHeight());
                productTb.setProductAccRequestCustomer(f.getFinishedGoodsAcc());
                productTb.setProductWeightRequestCustomer(f.getFinishedGoodsWeight());
                productTb.setFactoryMaterialPn(finishedGoodsListTb.getFactoryMaterialPn());
                productTb.setShopAddr(f.getDrawingFilesAddr());
//            productTb.setUploadAddr(customerCartTb.getUploadAddr());
                productTb.setHomeAddr(customerCartTb.getImgAddr());
                productTb.setDescription(finishedGoodsListTb.getFinishedGoodsDescription());
//            productTb.setDiyComment(customerCartTb.getComment());
                productTb.setPrice(customerCartTb.getUnitPrice());
                productTb.setDownloadAddr(null);
                productTb.setLogistic(null);
                productTb.setLogisticName(null);
                productTb.setCustomerCartID(customerCartTb.getCustomerCartId());
                productTbService.save(productTb);
            }

        }
        List<CartImgTb> cartImgTbs = cartImgTbMapper.getStatus(accountId);
        ProductImgTb productImgTb = new ProductImgTb();
        for (CartImgTb c : cartImgTbs) {
            productImgTb.setCustomerCartID(c.getCustomerCartId());
            productImgTb.setProductImg(c.getCartImg());
            productImgTb.setCommentBz(c.getCommentBz());
            productImgTbService.save(productImgTb);
        }
        //删除购物车
        for (CustomerCartTb customerCartTb : accountCart) {
            customerCartTbService.deiCart(customerCartTb.getCustomerCartId(), accountId);
        }
        return purchaseOrderTb.getPurchaseOrderNumberId();
    }

    //未支付的订单
    @Override
    public List<PurchaseOrderTb> Unpaid(Integer accountId) {
        return purchaseOrderTbMapper.UnpaidOrders(accountId);
    }

    //立即购买1
    @Override
    public Map<String, Integer> addPurchase(Integer accountId, Integer receiverId, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice, String remarks, Integer totalPoints, String id, int couponId, Double coupon, Double surplus) {
        String returnCode = orderCodeFactory.getReturnCode(accountId);
        String orderCode = orderCodeFactory.getOrderCode(accountId);
        PurchaseOrderTb purchaseOrderTb = new PurchaseOrderTb();
        ProductTb productTb = new ProductTb();
//        String purchaseOrderNumber = purchaseOrderTbMapper.purchaseOrderNumber();
        PersonRegisterTb personRegisterTb = personRegisterTbService.getById(accountId);
        ReceiverTb receiverTb = receiverTbService.getById(receiverId);
        FinishedGoodsListTb finishedGoodsListTb = finishedGoodsListTbService.getFinished(purchaseOrderProductPnID);
//        int result = Integer.valueOf(purchaseOrderNumber.substring(purchaseOrderNumber.length() - 5));
//        int num = result + 1;
        Date time = new Date();
//        Timestamp timeStamp = new Timestamp(time.getTime());
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (finishedGoodsListSubId == 0) {
            String Lqi = personRegisterTb.getAccountCode();
            if (Lqi.equals("LQI")) {
                purchaseOrderTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                purchaseOrderTb.setPurchaseOrderNumber(returnCode);
            }
            purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(receiverTb.getReceiverAddr() + receiverTb.getDetailsAddr());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(receiverTb.getReceiverName());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(receiverTb.getReceiverPhone());
            purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
            purchaseOrderTb.setPurchaseOrderSalesNames(id);
            purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
            purchaseOrderTb.setOrderHeader(finishedGoodsListTb.getFinishedGoodsName());
            purchaseOrderTb.setAccountId(accountId);
            purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
            purchaseOrderTb.setPayTime(df2.format(time));
            purchaseOrderTb.setOrderType("待支付");
            purchaseOrderTb.setRemarks(remarks);
            purchaseOrderTb.setStatus(0);
            purchaseOrderTb.setDeal("待处理");
            purchaseOrderTb.setTotalPoints(totalPoints);
            purchaseOrderTb.setYuanDing(null);
            purchaseOrderTb.setCouponId(couponId);
            purchaseOrderTb.setCoupon(coupon);
            purchaseOrderTbService.save(purchaseOrderTb);
            if (surplus != null) {
                CouponTb couponTb = new CouponTb();
                CouponTb coupon1 = couponTbMapper.coupon(couponId);
                couponTb.setAccountId(accountId);
                couponTb.setCouponPrice(surplus);
                couponTb.setBz(0);
                couponTb.setStartTime(coupon1.getStartTime());
                couponTb.setEndTime(coupon1.getEndTime());
                couponTbMapper.insert(couponTb);
            }
            //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
            if (Lqi.equals("LQI")) {
                productTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                productTb.setPurchaseOrderNumber(returnCode);
            }
            productTb.setPurchaseOrderProductPn(finishedGoodsListTb.getPurchaseOrderProductPn());
            productTb.setPurchaseOrderProductPnId(finishedGoodsListTb.getPurchaseOrderProductPnId());
            productTb.setPurchaseOrderProductName(finishedGoodsListTb.getFinishedGoodsName());
            productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
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
        }
        if (purchaseOrderProductPnID == 0){
            FinishedGoodsListSubTb f = finishedGoodsListSubTbMapper.f(finishedGoodsListSubId);
            Integer purchaseOrderProductPnId = f.getPurchaseOrderProductPnId();
            FinishedGoodsListTb byId = finishedGoodsListTbService.getById(purchaseOrderProductPnId);
            String Lqi = personRegisterTb.getAccountCode();
            if (Lqi.equals("LQI")) {
                purchaseOrderTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                purchaseOrderTb.setPurchaseOrderNumber(returnCode);
            }
            purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(receiverTb.getReceiverAddr() + receiverTb.getDetailsAddr());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(receiverTb.getReceiverName());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(receiverTb.getReceiverPhone());
            purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
            purchaseOrderTb.setPurchaseOrderSalesNames(id);
            purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
            purchaseOrderTb.setOrderHeader(f.getFinishedGoodsName());
            purchaseOrderTb.setAccountId(accountId);
            purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
            purchaseOrderTb.setPayTime(df2.format(time));
            purchaseOrderTb.setOrderType("待支付");
            purchaseOrderTb.setRemarks(remarks);
            purchaseOrderTb.setStatus(0);
            purchaseOrderTb.setDeal("待处理");
            purchaseOrderTb.setTotalPoints(totalPoints);
            purchaseOrderTb.setYuanDing(null);
            purchaseOrderTb.setCouponId(couponId);
            purchaseOrderTb.setCoupon(coupon);
            purchaseOrderTbService.save(purchaseOrderTb);
            if (surplus != null) {
                CouponTb couponTb = new CouponTb();
                CouponTb coupon1 = couponTbMapper.coupon(couponId);
                couponTb.setAccountId(accountId);
                couponTb.setCouponPrice(surplus);
                couponTb.setBz(0);
                couponTb.setStartTime(coupon1.getStartTime());
                couponTb.setEndTime(coupon1.getEndTime());
                couponTbMapper.insert(couponTb);
            }
            //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
            if (Lqi.equals("LQI")) {
                productTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                productTb.setPurchaseOrderNumber(returnCode);
            }
            productTb.setPurchaseOrderProductPn(f.getPurchaseOrderProductPn());
            productTb.setPurchaseOrderProductPnId(f.getPurchaseOrderProductPnId());
            productTb.setPurchaseOrderProductName(f.getFinishedGoodsName());
            productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
            productTb.setPrinterModelRequestCustomer(byId.getPrinterModelRequestCustomer());
            productTb.setFactoryMaterialName(f.getFinishedGoodsTexture());
            productTb.setColorRequestCustomer(f.getFinishedGoodsColor());
            productTb.setProductLengthRequestCustomer(f.getFinishedGoodsLength());
            productTb.setProductWidthRequestCustomer(f.getFinishedGoodsWidth());
            productTb.setProductHeightRequestCustomer(f.getFinishedGoodsHeight());
            productTb.setProductAccRequestCustomer(f.getFinishedGoodsAcc());
            productTb.setProductWeightRequestCustomer(f.getFinishedGoodsWeight());
            productTb.setFactoryMaterialPn(byId.getFactoryMaterialPn());
            productTb.setShopAddr(f.getDrawingFilesAddr());
//        productTb.setUploadAddr(uploadTb.getUploadAddr());
            productTb.setDescription(byId.getFinishedGoodsDescription());
//        productTb.setDiyComment(uploadTb.getDescription());
            productTb.setHomeAddr(f.getHomePageAddr());
            productTb.setPrice(f.getFinishedGoodsCustomerPriced());
            productTb.setDownloadAddr(null);
            productTb.setLogistic(null);
            productTb.setLogisticName(null);
            productTbService.save(productTb);
//        return productTbMapper.productId();
//        return purchaseOrderTb.getPurchaseOrderNumberId();
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("productId", productTbMapper.productId());
        map.put("purchaseOrderNumberId", purchaseOrderTb.getPurchaseOrderNumberId());
        return map;
    }

    //取消订单
    @Override
    public void delPurchase(String purchaseOrderNumber) {
        purchaseOrderTbMapper.delPurchase(purchaseOrderNumber);
        PurchaseOrderTb r = purchaseOrderTbMapper.r(purchaseOrderNumber);
        if (r.getCouponId()!=0){
            CouponTb couponTb = new CouponTb();
            CouponTb coupon1 = couponTbMapper.coupon(r.getCouponId());
            couponTb.setAccountId(r.getAccountId());
            couponTb.setCouponPrice(r.getCoupon());
            couponTb.setBz(0);
            couponTb.setStartTime(coupon1.getStartTime());
            couponTb.setEndTime(coupon1.getEndTime());
            couponTbMapper.insert(couponTb);
        }
    }

    //获取支付完成待处理的订单
    @Override
    public List<PurchaseOrderTb> ListPurchase() {
        return purchaseOrderTbMapper.allPurchase();
    }

    //处理完成
    @Override
    public void updatePurchase(String purchaseOrderNumber) {
        purchaseOrderTbMapper.updatePurchase(purchaseOrderNumber);
    }

    //修改未支付的地址
    @Override
    public void updateReceiver(String purchaseOrderNumber, Integer receiverId) {
        ReceiverTb byId = receiverTbService.getById(receiverId);
        purchaseOrderTbMapper.updateReceiver(byId.getReceiverAddr() + byId.getDetailsAddr(), byId.getReceiverName(), byId.getReceiverPhone(), purchaseOrderNumber);
    }

    //立即购买2
    @Override
    public Map<String, Integer> addPurchase2(Integer accountId, Integer receiverId, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice, String remarks, String diyRemarks, Integer totalPoints, String id, int couponId, Double coupon, Double surplus) {
        String returnCode = orderCodeFactory.getReturnCode(accountId);
        String orderCode = orderCodeFactory.getOrderCode(accountId);
        PurchaseOrderTb purchaseOrderTb = new PurchaseOrderTb();
        ProductTb productTb = new ProductTb();
//        String purchaseOrderNumber = purchaseOrderTbMapper.purchaseOrderNumber();
        PersonRegisterTb personRegisterTb = personRegisterTbService.getById(accountId);
        ReceiverTb receiverTb = receiverTbService.getById(receiverId);
        FinishedGoodsListTb finishedGoodsListTb = finishedGoodsListTbService.getFinished(purchaseOrderProductPnID);
//        int result = Integer.valueOf(purchaseOrderNumber.substring(purchaseOrderNumber.length() - 5));
//        int num = result + 1;
        Date time = new Date();
//        Timestamp timeStamp = new Timestamp(time.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (finishedGoodsListSubId == 0) {
            String Lqi = personRegisterTb.getAccountCode();
            if (Lqi.equals("LQI")) {
                purchaseOrderTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                purchaseOrderTb.setPurchaseOrderNumber(returnCode);
            }
            purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(receiverTb.getReceiverAddr() + receiverTb.getDetailsAddr());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(receiverTb.getReceiverName());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(receiverTb.getReceiverPhone());
            purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
            purchaseOrderTb.setPurchaseOrderSalesNames(id);
            purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
            purchaseOrderTb.setOrderHeader(finishedGoodsListTb.getFinishedGoodsName());
            purchaseOrderTb.setAccountId(accountId);
            purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
            purchaseOrderTb.setPayTime(df2.format(time));
            purchaseOrderTb.setOrderType("待支付");
            purchaseOrderTb.setRemarks(remarks);
            purchaseOrderTb.setStatus(0);
            purchaseOrderTb.setDeal("待处理");
            purchaseOrderTb.setTotalPoints(totalPoints);
            purchaseOrderTb.setYuanDing(null);
            purchaseOrderTb.setCouponId(couponId);
            purchaseOrderTb.setCoupon(coupon);
            purchaseOrderTbService.save(purchaseOrderTb);
            if (surplus != null) {
                CouponTb couponTb = new CouponTb();
                CouponTb coupon1 = couponTbMapper.coupon(couponId);
                couponTb.setAccountId(accountId);
                couponTb.setCouponPrice(surplus);
                couponTb.setBz(0);
                couponTb.setStartTime(coupon1.getStartTime());
                couponTb.setEndTime(coupon1.getEndTime());
                couponTbMapper.insert(couponTb);
            }
            //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
            if (Lqi.equals("LQI")) {
                productTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                productTb.setPurchaseOrderNumber(returnCode);
            }
            productTb.setPurchaseOrderProductPn(finishedGoodsListTb.getPurchaseOrderProductPn());
            productTb.setPurchaseOrderProductPnId(finishedGoodsListTb.getPurchaseOrderProductPnId());
            productTb.setPurchaseOrderProductName(finishedGoodsListTb.getFinishedGoodsName());
            productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
            productTb.setPrinterModelRequestCustomer(finishedGoodsListTb.getFactoryMaterialPn());
            productTb.setFactoryMaterialName(finishedGoodsListTb.getFinishedGoodsTexture());
            productTb.setColorRequestCustomer(finishedGoodsListTb.getFinishedGoodsColor());
            productTb.setProductLengthRequestCustomer(finishedGoodsListTb.getFinishedGoodsLength());
            productTb.setProductWidthRequestCustomer(finishedGoodsListTb.getFinishedGoodsWidth());
            productTb.setProductHeightRequestCustomer(finishedGoodsListTb.getFinishedGoodsHeight());
            productTb.setProductAccRequestCustomer(finishedGoodsListTb.getFinishedGoodsAcc());
            productTb.setProductWeightRequestCustomer(finishedGoodsListTb.getFinishedGoodsWeight());
            productTb.setFactoryMaterialPn(finishedGoodsListTb.getFactoryMaterialPn());
            productTb.setShopAddr(finishedGoodsListTb.getDrawingFilesAddr());
//        productTb.setUploadAddr(null);
            productTb.setDescription(finishedGoodsListTb.getFinishedGoodsDescription());
//        productTb.setDiyComment(diyRemarks);
            productTb.setPrice(finishedGoodsListTb.getFinishedGoodsCustomerPriced());
            productTb.setHomeAddr(finishedGoodsListTb.getHomePageAddr());
            productTb.setDownloadAddr(null);
            productTb.setLogistic(null);
            productTb.setLogisticName(null);
            productTbService.save(productTb);
        }
        if (purchaseOrderProductPnID == 0){
            FinishedGoodsListSubTb f = finishedGoodsListSubTbMapper.f(finishedGoodsListSubId);
            Integer purchaseOrderProductPnId = f.getPurchaseOrderProductPnId();
            FinishedGoodsListTb byId = finishedGoodsListTbService.getById(purchaseOrderProductPnId);
            String Lqi = personRegisterTb.getAccountCode();
            if (Lqi.equals("LQI")) {
                purchaseOrderTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                purchaseOrderTb.setPurchaseOrderNumber(returnCode);
            }
            purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(receiverTb.getReceiverAddr() + receiverTb.getDetailsAddr());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(receiverTb.getReceiverName());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(receiverTb.getReceiverPhone());
            purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
            purchaseOrderTb.setPurchaseOrderSalesNames(id);
            purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
            purchaseOrderTb.setOrderHeader(f.getFinishedGoodsName());
            purchaseOrderTb.setAccountId(accountId);
            purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
            purchaseOrderTb.setPayTime(df2.format(time));
            purchaseOrderTb.setOrderType("待支付");
            purchaseOrderTb.setRemarks(remarks);
            purchaseOrderTb.setStatus(0);
            purchaseOrderTb.setDeal("待处理");
            purchaseOrderTb.setTotalPoints(totalPoints);
            purchaseOrderTb.setYuanDing(null);
            purchaseOrderTb.setCouponId(couponId);
            purchaseOrderTb.setCoupon(coupon);
            purchaseOrderTbService.save(purchaseOrderTb);
            if (surplus != null) {
                CouponTb couponTb = new CouponTb();
                CouponTb coupon1 = couponTbMapper.coupon(couponId);
                couponTb.setAccountId(accountId);
                couponTb.setCouponPrice(surplus);
                couponTb.setBz(0);
                couponTb.setStartTime(coupon1.getStartTime());
                couponTb.setEndTime(coupon1.getEndTime());
                couponTbMapper.insert(couponTb);
            }
            //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
            if (Lqi.equals("LQI")) {
                productTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                productTb.setPurchaseOrderNumber(returnCode);
            }
            productTb.setPurchaseOrderProductPn(f.getPurchaseOrderProductPn());
            productTb.setPurchaseOrderProductPnId(f.getPurchaseOrderProductPnId());
            productTb.setPurchaseOrderProductName(f.getFinishedGoodsName());
            productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
            productTb.setPrinterModelRequestCustomer(byId.getFactoryMaterialPn());
            productTb.setFactoryMaterialName(f.getFinishedGoodsTexture());
            productTb.setColorRequestCustomer(f.getFinishedGoodsColor());
            productTb.setProductLengthRequestCustomer(f.getFinishedGoodsLength());
            productTb.setProductWidthRequestCustomer(f.getFinishedGoodsWidth());
            productTb.setProductHeightRequestCustomer(f.getFinishedGoodsHeight());
            productTb.setProductAccRequestCustomer(f.getFinishedGoodsAcc());
            productTb.setProductWeightRequestCustomer(f.getFinishedGoodsWeight());
            productTb.setFactoryMaterialPn(byId.getFactoryMaterialPn());
            productTb.setShopAddr(f.getDrawingFilesAddr());
//        productTb.setUploadAddr(null);
            productTb.setDescription(byId.getFinishedGoodsDescription());
//        productTb.setDiyComment(diyRemarks);
            productTb.setPrice(f.getFinishedGoodsCustomerPriced());
            productTb.setHomeAddr(f.getHomePageAddr());
            productTb.setDownloadAddr(null);
            productTb.setLogistic(null);
            productTb.setLogisticName(null);
            productTbService.save(productTb);
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("productId", productTbMapper.productId());
        map.put("purchaseOrderNumberId", purchaseOrderTb.getPurchaseOrderNumberId());
        return map;
    }

    //立即购买0
    @Override
    public Map<String, Integer> addPurchase0(Integer accountId, Integer receiverId, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice, String remarks, Integer totalPoints, String id, int couponId, Double coupon, Double surplus) {
        String returnCode = orderCodeFactory.getReturnCode(accountId);
        String orderCode = orderCodeFactory.getOrderCode(accountId);
        PurchaseOrderTb purchaseOrderTb = new PurchaseOrderTb();
        ProductTb productTb = new ProductTb();
//        String purchaseOrderNumber = purchaseOrderTbMapper.purchaseOrderNumber();
        PersonRegisterTb personRegisterTb = personRegisterTbService.getById(accountId);
        ReceiverTb receiverTb = receiverTbService.getById(receiverId);
        FinishedGoodsListTb finishedGoodsListTb = finishedGoodsListTbService.getFinished(purchaseOrderProductPnID);
//        int result = Integer.valueOf(purchaseOrderNumber.substring(purchaseOrderNumber.length() - 5));
//        int num = result + 1;
        Date time = new Date();
//        Timestamp timeStamp = new Timestamp(time.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (finishedGoodsListSubId == 0){
            String Lqi = personRegisterTb.getAccountCode();
            if (Lqi.equals("LQI")) {
                purchaseOrderTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                purchaseOrderTb.setPurchaseOrderNumber(returnCode);
            }
            purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(receiverTb.getReceiverAddr() + receiverTb.getDetailsAddr());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(receiverTb.getReceiverName());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(receiverTb.getReceiverPhone());
            purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
            purchaseOrderTb.setPurchaseOrderSalesNames(id);
            purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
            purchaseOrderTb.setOrderHeader(finishedGoodsListTb.getFinishedGoodsName());
            purchaseOrderTb.setAccountId(accountId);
            purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
            purchaseOrderTb.setPayTime(df2.format(time));
            purchaseOrderTb.setOrderType("待支付");
            purchaseOrderTb.setRemarks(remarks);
            purchaseOrderTb.setStatus(0);
            purchaseOrderTb.setDeal("待处理");
            purchaseOrderTb.setTotalPoints(totalPoints);
            purchaseOrderTb.setYuanDing(null);
            purchaseOrderTb.setCouponId(couponId);
            purchaseOrderTb.setCoupon(coupon);
            purchaseOrderTbService.save(purchaseOrderTb);
            if (surplus != null) {
                CouponTb couponTb = new CouponTb();
                CouponTb coupon1 = couponTbMapper.coupon(couponId);
                couponTb.setAccountId(accountId);
                couponTb.setCouponPrice(surplus);
                couponTb.setBz(0);
                couponTb.setStartTime(coupon1.getStartTime());
                couponTb.setEndTime(coupon1.getEndTime());
                couponTbMapper.insert(couponTb);
            }
            //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
            if (Lqi.equals("LQI")) {
                productTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                productTb.setPurchaseOrderNumber(returnCode);
            }
            productTb.setPurchaseOrderProductPn(finishedGoodsListTb.getPurchaseOrderProductPn());
            productTb.setPurchaseOrderProductPnId(finishedGoodsListTb.getPurchaseOrderProductPnId());
            productTb.setPurchaseOrderProductName(finishedGoodsListTb.getFinishedGoodsName());
            productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
            productTb.setPrinterModelRequestCustomer(finishedGoodsListTb.getFactoryMaterialPn());
            productTb.setFactoryMaterialName(finishedGoodsListTb.getFinishedGoodsTexture());
            productTb.setColorRequestCustomer(finishedGoodsListTb.getFinishedGoodsColor());
            productTb.setProductLengthRequestCustomer(finishedGoodsListTb.getFinishedGoodsLength());
            productTb.setProductWidthRequestCustomer(finishedGoodsListTb.getFinishedGoodsWidth());
            productTb.setProductHeightRequestCustomer(finishedGoodsListTb.getFinishedGoodsHeight());
            productTb.setProductAccRequestCustomer(finishedGoodsListTb.getFinishedGoodsAcc());
            productTb.setProductWeightRequestCustomer(finishedGoodsListTb.getFinishedGoodsWeight());
            productTb.setFactoryMaterialPn(finishedGoodsListTb.getFactoryMaterialPn());
            productTb.setShopAddr(finishedGoodsListTb.getDrawingFilesAddr());
//        productTb.setUploadAddr(null);
            productTb.setDescription(finishedGoodsListTb.getFinishedGoodsDescription());
//        productTb.setDiyComment(null);
            productTb.setPrice(finishedGoodsListTb.getFinishedGoodsCustomerPriced());
            productTb.setHomeAddr(finishedGoodsListTb.getHomePageAddr());
            productTb.setDownloadAddr(null);
            productTb.setLogistic(null);
            productTb.setLogisticName(null);
            productTbService.save(productTb);
        }
        if (purchaseOrderProductPnID == 0){
            FinishedGoodsListSubTb f = finishedGoodsListSubTbMapper.f(finishedGoodsListSubId);
            Integer purchaseOrderProductPnId = f.getPurchaseOrderProductPnId();
            FinishedGoodsListTb byId = finishedGoodsListTbService.getById(purchaseOrderProductPnId);
            String Lqi = personRegisterTb.getAccountCode();
            if (Lqi.equals("LQI")) {
                purchaseOrderTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                purchaseOrderTb.setPurchaseOrderNumber(returnCode);
            }
            purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(receiverTb.getReceiverAddr() + receiverTb.getDetailsAddr());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(receiverTb.getReceiverName());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(receiverTb.getReceiverPhone());
            purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
            purchaseOrderTb.setPurchaseOrderSalesNames(id);
            purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
            purchaseOrderTb.setOrderHeader(f.getFinishedGoodsName());
            purchaseOrderTb.setAccountId(accountId);
            purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
            purchaseOrderTb.setPayTime(df2.format(time));
            purchaseOrderTb.setOrderType("待支付");
            purchaseOrderTb.setRemarks(remarks);
            purchaseOrderTb.setStatus(0);
            purchaseOrderTb.setDeal("待处理");
            purchaseOrderTb.setTotalPoints(totalPoints);
            purchaseOrderTb.setYuanDing(null);
            purchaseOrderTb.setCouponId(couponId);
            purchaseOrderTb.setCoupon(coupon);
            purchaseOrderTbService.save(purchaseOrderTb);
            if (surplus != null) {
                CouponTb couponTb = new CouponTb();
                CouponTb coupon1 = couponTbMapper.coupon(couponId);
                couponTb.setAccountId(accountId);
                couponTb.setCouponPrice(surplus);
                couponTb.setBz(0);
                couponTb.setStartTime(coupon1.getStartTime());
                couponTb.setEndTime(coupon1.getEndTime());
                couponTbMapper.insert(couponTb);
            }
            //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
            if (Lqi.equals("LQI")) {
                productTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                productTb.setPurchaseOrderNumber(returnCode);
            }
            productTb.setPurchaseOrderProductPn(f.getPurchaseOrderProductPn());
            productTb.setPurchaseOrderProductPnId(f.getPurchaseOrderProductPnId());
            productTb.setPurchaseOrderProductName(f.getFinishedGoodsName());
            productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
            productTb.setPrinterModelRequestCustomer(byId.getFactoryMaterialPn());
            productTb.setFactoryMaterialName(f.getFinishedGoodsTexture());
            productTb.setColorRequestCustomer(f.getFinishedGoodsColor());
            productTb.setProductLengthRequestCustomer(f.getFinishedGoodsLength());
            productTb.setProductWidthRequestCustomer(f.getFinishedGoodsWidth());
            productTb.setProductHeightRequestCustomer(f.getFinishedGoodsHeight());
            productTb.setProductAccRequestCustomer(f.getFinishedGoodsAcc());
            productTb.setProductWeightRequestCustomer(f.getFinishedGoodsWeight());
            productTb.setFactoryMaterialPn(byId.getFactoryMaterialPn());
            productTb.setShopAddr(f.getDrawingFilesAddr());
//        productTb.setUploadAddr(null);
            productTb.setDescription(byId.getFinishedGoodsDescription());
//        productTb.setDiyComment(null);
            productTb.setPrice(f.getFinishedGoodsCustomerPriced());
            productTb.setHomeAddr(f.getHomePageAddr());
            productTb.setDownloadAddr(null);
            productTb.setLogistic(null);
            productTb.setLogisticName(null);
            productTbService.save(productTb);
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("productId", productTbMapper.productId());
        map.put("purchaseOrderNumberId", purchaseOrderTb.getPurchaseOrderNumberId());
        return map;
    }

    //为他人购买1
    @Override
    public Map<String, Integer> purchasingAgent1(Integer accountId, String yuanDing, String name, String tel, String addr, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice, String remarks, Integer totalPoints, String id) {
        String replenishmentCode = orderCodeFactory.getReplenishmentCode(accountId);
        PurchaseOrderTb purchaseOrderTb = new PurchaseOrderTb();
        ProductTb productTb = new ProductTb();
//        String purchaseOrderNumber = purchaseOrderTbMapper.purchaseOrderNumber();
        PersonRegisterTb personRegisterTb = personRegisterTbService.getById(accountId);
        FinishedGoodsListTb finishedGoodsListTb = finishedGoodsListTbService.getById(purchaseOrderProductPnID);
//        int result = Integer.valueOf(purchaseOrderNumber.substring(purchaseOrderNumber.length() - 5));
//        int num = result + 1;
        Date time = new Date();
//        Timestamp timeStamp = new Timestamp(time.getTime());
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (finishedGoodsListSubId == 0){
            purchaseOrderTb.setPurchaseOrderNumber(replenishmentCode);
            purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(addr);
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(name);
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(tel);
            purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
            purchaseOrderTb.setPurchaseOrderSalesNames(id);
            purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
            purchaseOrderTb.setOrderHeader(finishedGoodsListTb.getFinishedGoodsName());
            purchaseOrderTb.setAccountId(accountId);
            purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
            purchaseOrderTb.setPayTime(df2.format(time));
            purchaseOrderTb.setOrderType("支付完成");
            purchaseOrderTb.setRemarks(remarks);
            purchaseOrderTb.setStatus(0);
            purchaseOrderTb.setDeal("待处理");
            purchaseOrderTb.setTotalPoints(0);
            purchaseOrderTb.setYuanDing(yuanDing);
            purchaseOrderTbService.save(purchaseOrderTb);
            //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
            productTb.setPurchaseOrderNumber(replenishmentCode);
            productTb.setPurchaseOrderProductPn(finishedGoodsListTb.getPurchaseOrderProductPn());
            productTb.setPurchaseOrderProductPnId(finishedGoodsListTb.getPurchaseOrderProductPnId());
            productTb.setPurchaseOrderProductName(finishedGoodsListTb.getFinishedGoodsName());
            productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
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
        }
        if (purchaseOrderProductPnID == 0){
            FinishedGoodsListSubTb f = finishedGoodsListSubTbMapper.f(finishedGoodsListSubId);
            Integer purchaseOrderProductPnId = f.getPurchaseOrderProductPnId();
            FinishedGoodsListTb byId = finishedGoodsListTbService.getById(purchaseOrderProductPnId);
            purchaseOrderTb.setPurchaseOrderNumber(replenishmentCode);
            purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(addr);
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(name);
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(tel);
            purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
            purchaseOrderTb.setPurchaseOrderSalesNames(id);
            purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
            purchaseOrderTb.setOrderHeader(f.getFinishedGoodsName());
            purchaseOrderTb.setAccountId(accountId);
            purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
            purchaseOrderTb.setPayTime(df2.format(time));
            purchaseOrderTb.setOrderType("支付完成");
            purchaseOrderTb.setRemarks(remarks);
            purchaseOrderTb.setStatus(0);
            purchaseOrderTb.setDeal("待处理");
            purchaseOrderTb.setTotalPoints(0);
            purchaseOrderTb.setYuanDing(yuanDing);
            purchaseOrderTbService.save(purchaseOrderTb);
            //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
            productTb.setPurchaseOrderNumber(replenishmentCode);
            productTb.setPurchaseOrderProductPn(f.getPurchaseOrderProductPn());
            productTb.setPurchaseOrderProductPnId(f.getPurchaseOrderProductPnId());
            productTb.setPurchaseOrderProductName(f.getFinishedGoodsName());
            productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
            productTb.setPrinterModelRequestCustomer(byId.getPrinterModelRequestCustomer());
            productTb.setFactoryMaterialName(f.getFinishedGoodsTexture());
            productTb.setColorRequestCustomer(f.getFinishedGoodsColor());
            productTb.setProductLengthRequestCustomer(f.getFinishedGoodsLength());
            productTb.setProductWidthRequestCustomer(f.getFinishedGoodsWidth());
            productTb.setProductHeightRequestCustomer(f.getFinishedGoodsHeight());
            productTb.setProductAccRequestCustomer(f.getFinishedGoodsAcc());
            productTb.setProductWeightRequestCustomer(f.getFinishedGoodsWeight());
            productTb.setFactoryMaterialPn(byId.getFactoryMaterialPn());
            productTb.setShopAddr(f.getDrawingFilesAddr());
//        productTb.setUploadAddr(uploadTb.getUploadAddr());
            productTb.setDescription(byId.getFinishedGoodsDescription());
//        productTb.setDiyComment(uploadTb.getDescription());
            productTb.setHomeAddr(f.getHomePageAddr());
            productTb.setPrice(f.getFinishedGoodsCustomerPriced());
            productTb.setDownloadAddr(null);
            productTb.setLogistic(null);
            productTb.setLogisticName(null);
            productTbService.save(productTb);
//        return productTbMapper.productId();
//        return purchaseOrderTb.getPurchaseOrderNumberId();
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("productId", productTbMapper.productId());
        map.put("purchaseOrderNumberId", purchaseOrderTb.getPurchaseOrderNumberId());
        return map;
    }

    //为他人购买2
    @Override
    public Map<String, Integer> purchasingAgent2(Integer accountId, String name, String tel, String addr, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice, String remarks, String diyRemarks, Integer totalPoints, String id) {
        String returnCode = orderCodeFactory.getReturnCode(accountId);
        String orderCode = orderCodeFactory.getOrderCode(accountId);
        PurchaseOrderTb purchaseOrderTb = new PurchaseOrderTb();
        ProductTb productTb = new ProductTb();
//        String purchaseOrderNumber = purchaseOrderTbMapper.purchaseOrderNumber();
        PersonRegisterTb personRegisterTb = personRegisterTbService.getById(accountId);
        FinishedGoodsListTb finishedGoodsListTb = finishedGoodsListTbService.getById(purchaseOrderProductPnID);
//        int result = Integer.valueOf(purchaseOrderNumber.substring(purchaseOrderNumber.length() - 5));
//        int num = result + 1;
        Date time = new Date();
//        Timestamp timeStamp = new Timestamp(time.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (finishedGoodsListSubId == 0){
            String Lqi = personRegisterTb.getAccountCode();
            if (Lqi.equals("LQI")) {
                purchaseOrderTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                purchaseOrderTb.setPurchaseOrderNumber(returnCode);
            }
            purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(addr);
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(name);
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(tel);
            purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
            purchaseOrderTb.setPurchaseOrderSalesNames(id);
            purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
            purchaseOrderTb.setOrderHeader(finishedGoodsListTb.getFinishedGoodsName());
            purchaseOrderTb.setAccountId(accountId);
            purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
            purchaseOrderTb.setPayTime(df2.format(time));
            purchaseOrderTb.setOrderType("待支付");
            purchaseOrderTb.setRemarks(remarks);
            purchaseOrderTb.setStatus(0);
            purchaseOrderTb.setDeal("待处理");
            purchaseOrderTb.setTotalPoints(totalPoints);
            purchaseOrderTbService.save(purchaseOrderTb);
            //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
            if (Lqi.equals("LQI")) {
                productTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                productTb.setPurchaseOrderNumber(returnCode);
            }
            productTb.setPurchaseOrderProductPn(finishedGoodsListTb.getPurchaseOrderProductPn());
            productTb.setPurchaseOrderProductPnId(finishedGoodsListTb.getPurchaseOrderProductPnId());
            productTb.setPurchaseOrderProductName(finishedGoodsListTb.getFinishedGoodsName());
            productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
            productTb.setPrinterModelRequestCustomer(finishedGoodsListTb.getFactoryMaterialPn());
            productTb.setFactoryMaterialName(finishedGoodsListTb.getFinishedGoodsTexture());
            productTb.setColorRequestCustomer(finishedGoodsListTb.getFinishedGoodsColor());
            productTb.setProductLengthRequestCustomer(finishedGoodsListTb.getFinishedGoodsLength());
            productTb.setProductWidthRequestCustomer(finishedGoodsListTb.getFinishedGoodsWidth());
            productTb.setProductHeightRequestCustomer(finishedGoodsListTb.getFinishedGoodsHeight());
            productTb.setProductAccRequestCustomer(finishedGoodsListTb.getFinishedGoodsAcc());
            productTb.setProductWeightRequestCustomer(finishedGoodsListTb.getFinishedGoodsWeight());
            productTb.setFactoryMaterialPn(finishedGoodsListTb.getFactoryMaterialPn());
            productTb.setShopAddr(finishedGoodsListTb.getDrawingFilesAddr());
//        productTb.setUploadAddr(null);
            productTb.setDescription(finishedGoodsListTb.getFinishedGoodsDescription());
//        productTb.setDiyComment(diyRemarks);
            productTb.setPrice(finishedGoodsListTb.getFinishedGoodsCustomerPriced());
            productTb.setHomeAddr(finishedGoodsListTb.getHomePageAddr());
            productTb.setDownloadAddr(null);
            productTb.setLogistic(null);
            productTb.setLogisticName(null);
            productTbService.save(productTb);
        }
        if (purchaseOrderProductPnID == 0){
            FinishedGoodsListSubTb f = finishedGoodsListSubTbMapper.f(finishedGoodsListSubId);
            Integer purchaseOrderProductPnId = f.getPurchaseOrderProductPnId();
            FinishedGoodsListTb byId = finishedGoodsListTbService.getById(purchaseOrderProductPnId);
            String Lqi = personRegisterTb.getAccountCode();
            if (Lqi.equals("LQI")) {
                purchaseOrderTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                purchaseOrderTb.setPurchaseOrderNumber(returnCode);
            }
            purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(addr);
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(name);
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(tel);
            purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
            purchaseOrderTb.setPurchaseOrderSalesNames(id);
            purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
            purchaseOrderTb.setOrderHeader(f.getFinishedGoodsName());
            purchaseOrderTb.setAccountId(accountId);
            purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
            purchaseOrderTb.setPayTime(df2.format(time));
            purchaseOrderTb.setOrderType("待支付");
            purchaseOrderTb.setRemarks(remarks);
            purchaseOrderTb.setStatus(0);
            purchaseOrderTb.setDeal("待处理");
            purchaseOrderTb.setTotalPoints(totalPoints);
            purchaseOrderTbService.save(purchaseOrderTb);
            //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
            if (Lqi.equals("LQI")) {
                productTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                productTb.setPurchaseOrderNumber(returnCode);
            }
            productTb.setPurchaseOrderProductPn(f.getPurchaseOrderProductPn());
            productTb.setPurchaseOrderProductPnId(f.getPurchaseOrderProductPnId());
            productTb.setPurchaseOrderProductName(f.getFinishedGoodsName());
            productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
            productTb.setPrinterModelRequestCustomer(byId.getFactoryMaterialPn());
            productTb.setFactoryMaterialName(f.getFinishedGoodsTexture());
            productTb.setColorRequestCustomer(f.getFinishedGoodsColor());
            productTb.setProductLengthRequestCustomer(f.getFinishedGoodsLength());
            productTb.setProductWidthRequestCustomer(f.getFinishedGoodsWidth());
            productTb.setProductHeightRequestCustomer(f.getFinishedGoodsHeight());
            productTb.setProductAccRequestCustomer(f.getFinishedGoodsAcc());
            productTb.setProductWeightRequestCustomer(f.getFinishedGoodsWeight());
            productTb.setFactoryMaterialPn(byId.getFactoryMaterialPn());
            productTb.setShopAddr(f.getDrawingFilesAddr());
//        productTb.setUploadAddr(null);
            productTb.setDescription(byId.getFinishedGoodsDescription());
//        productTb.setDiyComment(diyRemarks);
            productTb.setPrice(f.getFinishedGoodsCustomerPriced());
            productTb.setHomeAddr(f.getHomePageAddr());
            productTb.setDownloadAddr(null);
            productTb.setLogistic(null);
            productTb.setLogisticName(null);
            productTbService.save(productTb);
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("productId", productTbMapper.productId());
        map.put("purchaseOrderNumberId", purchaseOrderTb.getPurchaseOrderNumberId());
        return map;
    }

    //为他人购买0
    @Override
    public Map<String, Integer> purchasingAgent0(Integer accountId, String name, String tel, String addr, Integer purchaseOrderProductPnID,Integer finishedGoodsListSubId, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice, String remarks, Integer totalPoints, String id) {
        String returnCode = orderCodeFactory.getReturnCode(accountId);
        String orderCode = orderCodeFactory.getOrderCode(accountId);
        PurchaseOrderTb purchaseOrderTb = new PurchaseOrderTb();
        ProductTb productTb = new ProductTb();
//        String purchaseOrderNumber = purchaseOrderTbMapper.purchaseOrderNumber();
        PersonRegisterTb personRegisterTb = personRegisterTbService.getById(accountId);
        FinishedGoodsListTb finishedGoodsListTb = finishedGoodsListTbService.getById(purchaseOrderProductPnID);
//        int result = Integer.valueOf(purchaseOrderNumber.substring(purchaseOrderNumber.length() - 5));
//        int num = result + 1;
        Date time = new Date();
//        Timestamp timeStamp = new Timestamp(time.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (finishedGoodsListSubId == 0){
            String Lqi = personRegisterTb.getAccountCode();
            if (Lqi.equals("LQI")) {
                purchaseOrderTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                purchaseOrderTb.setPurchaseOrderNumber(returnCode);
            }
            purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(addr);
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(name);
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(tel);
            purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
            purchaseOrderTb.setPurchaseOrderSalesNames(id);
            purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
            purchaseOrderTb.setOrderHeader(finishedGoodsListTb.getFinishedGoodsName());
            purchaseOrderTb.setAccountId(accountId);
            purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
            purchaseOrderTb.setPayTime(df2.format(time));
            purchaseOrderTb.setOrderType("待支付");
            purchaseOrderTb.setRemarks(remarks);
            purchaseOrderTb.setStatus(0);
            purchaseOrderTb.setDeal("待处理");
            purchaseOrderTb.setTotalPoints(totalPoints);
            purchaseOrderTbService.save(purchaseOrderTb);
            //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
            if (Lqi.equals("LQI")) {
                productTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                productTb.setPurchaseOrderNumber(returnCode);
            }
            productTb.setPurchaseOrderProductPn(finishedGoodsListTb.getPurchaseOrderProductPn());
            productTb.setPurchaseOrderProductPnId(finishedGoodsListTb.getPurchaseOrderProductPnId());
            productTb.setPurchaseOrderProductName(finishedGoodsListTb.getFinishedGoodsName());
            productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
            productTb.setPrinterModelRequestCustomer(finishedGoodsListTb.getFactoryMaterialPn());
            productTb.setFactoryMaterialName(finishedGoodsListTb.getFinishedGoodsTexture());
            productTb.setColorRequestCustomer(finishedGoodsListTb.getFinishedGoodsColor());
            productTb.setProductLengthRequestCustomer(finishedGoodsListTb.getFinishedGoodsLength());
            productTb.setProductWidthRequestCustomer(finishedGoodsListTb.getFinishedGoodsWidth());
            productTb.setProductHeightRequestCustomer(finishedGoodsListTb.getFinishedGoodsHeight());
            productTb.setProductAccRequestCustomer(finishedGoodsListTb.getFinishedGoodsAcc());
            productTb.setProductWeightRequestCustomer(finishedGoodsListTb.getFinishedGoodsWeight());
            productTb.setFactoryMaterialPn(finishedGoodsListTb.getFactoryMaterialPn());
            productTb.setShopAddr(finishedGoodsListTb.getDrawingFilesAddr());
//        productTb.setUploadAddr(null);
            productTb.setDescription(finishedGoodsListTb.getFinishedGoodsDescription());
//        productTb.setDiyComment(null);
            productTb.setPrice(finishedGoodsListTb.getFinishedGoodsCustomerPriced());
            productTb.setHomeAddr(finishedGoodsListTb.getHomePageAddr());
            productTb.setDownloadAddr(null);
            productTb.setLogistic(null);
            productTb.setLogisticName(null);
            productTbService.save(productTb);
        }
        if (purchaseOrderProductPnID == 0){
            FinishedGoodsListSubTb f = finishedGoodsListSubTbMapper.f(finishedGoodsListSubId);
            Integer purchaseOrderProductPnId = f.getPurchaseOrderProductPnId();
            FinishedGoodsListTb byId = finishedGoodsListTbService.getById(purchaseOrderProductPnId);
            String Lqi = personRegisterTb.getAccountCode();
            if (Lqi.equals("LQI")) {
                purchaseOrderTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                purchaseOrderTb.setPurchaseOrderNumber(returnCode);
            }
            purchaseOrderTb.setPurchaseOrderFrom(personRegisterTb.getBuyerCompanyName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindow(personRegisterTb.getAccountName());
            purchaseOrderTb.setPurchaseOrderCustomerContactWindowPhone(personRegisterTb.getPhoneNumber());
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddr(addr);
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindow(name);
            purchaseOrderTb.setPurchaseOrderCustomerShippingAddrContactWindowPhone(tel);
            purchaseOrderTb.setPurchaseOrderCustomerOrderDate(df2.format(time));
            purchaseOrderTb.setPurchaseOrderSalesNames(id);
            purchaseOrderTb.setPurchaseOrderSalesPhone("待处理");
            purchaseOrderTb.setOrderHeader(f.getFinishedGoodsName());
            purchaseOrderTb.setAccountId(accountId);
            purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
            purchaseOrderTb.setPayTime(df2.format(time));
            purchaseOrderTb.setOrderType("待支付");
            purchaseOrderTb.setRemarks(remarks);
            purchaseOrderTb.setStatus(0);
            purchaseOrderTb.setDeal("待处理");
            purchaseOrderTb.setTotalPoints(totalPoints);
            purchaseOrderTbService.save(purchaseOrderTb);
            //添加到物品清单
//        UploadTb uploadTb = uploadTbMapper.addr(accountId);
            if (Lqi.equals("LQI")) {
                productTb.setPurchaseOrderNumber(orderCode);
            }
            if (Lqi.equals("LQC")) {
                productTb.setPurchaseOrderNumber(returnCode);
            }
            productTb.setPurchaseOrderProductPn(f.getPurchaseOrderProductPn());
            productTb.setPurchaseOrderProductPnId(f.getPurchaseOrderProductPnId());
            productTb.setPurchaseOrderProductName(f.getFinishedGoodsName());
            productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
            productTb.setPrinterModelRequestCustomer(byId.getFactoryMaterialPn());
            productTb.setFactoryMaterialName(f.getFinishedGoodsTexture());
            productTb.setColorRequestCustomer(f.getFinishedGoodsColor());
            productTb.setProductLengthRequestCustomer(f.getFinishedGoodsLength());
            productTb.setProductWidthRequestCustomer(f.getFinishedGoodsWidth());
            productTb.setProductHeightRequestCustomer(f.getFinishedGoodsHeight());
            productTb.setProductAccRequestCustomer(f.getFinishedGoodsAcc());
            productTb.setProductWeightRequestCustomer(f.getFinishedGoodsWeight());
            productTb.setFactoryMaterialPn(byId.getFactoryMaterialPn());
            productTb.setShopAddr(f.getDrawingFilesAddr());
//        productTb.setUploadAddr(null);
            productTb.setDescription(byId.getFinishedGoodsDescription());
//        productTb.setDiyComment(null);
            productTb.setPrice(f.getFinishedGoodsCustomerPriced());
            productTb.setHomeAddr(f.getHomePageAddr());
            productTb.setDownloadAddr(null);
            productTb.setLogistic(null);
            productTb.setLogisticName(null);
            productTbService.save(productTb);
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("productId", productTbMapper.productId());
        map.put("purchaseOrderNumberId", purchaseOrderTb.getPurchaseOrderNumberId());
        return map;
    }

//    @Override
//    public List<PurchaseOrderTb> selectPurchaseOrderNumber() {
//        return purchaseOrderTbMapper.selectPurchaseOrderNumber();
//    }
}
