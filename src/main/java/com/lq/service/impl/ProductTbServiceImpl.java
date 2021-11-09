package com.lq.service.impl;

import com.lq.ShowApiRequest;
import com.lq.dao.*;
import com.lq.pojo.*;
import com.lq.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.utils.DealFile;
import com.lq.utils.OrderCodeFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
@Service
public class ProductTbServiceImpl extends ServiceImpl<ProductTbMapper, ProductTb> implements ProductTbService {

    @Resource
    private ProductTbMapper productTbMapper;
    @Resource
    private PurchaseOrderTbMapper purchaseOrderTbMapper;
    @Resource
    private PurchaseOrderTbService purchaseOrderTbService;
    @Resource
    private PersonRegisterTbService personRegisterTbService;
    @Resource
    private ReceiverTbService receiverTbService;
    @Resource
    private ProductTbService productTbService;
    @Resource
    private DealFile dealFile;
    @Resource
    private BuyNowImgTbMapper buyNowImgTbMapper;
    @Resource
    private BuyNowImgTbService buyNowImgTbService;

    @Resource
    private ProductImgTbMapper productImgTbMapper;


    //查看未支付物品清单
    @Override
    public List<ProductTb> allProduct(String purchaseOrderNumber) {
        return productTbMapper.all(purchaseOrderNumber);
    }

    //未完成的物品
    @Override
    public List<ProductTb> undoneProduct(Integer accountID) {
        return productTbMapper.undone(accountID);
    }

    //已完成的物品
    @Override
    public List<ProductTb> completedProduct(Integer accountID) {
        return productTbMapper.completed(accountID);
    }

    //查询物流
    @Override
    public String Logistics(String logistic,String phone) {
            if (logistic.length() == 13){
                String res = new ShowApiRequest("http://route.showapi.com/64-19", "691709", "dafba4849ef343fd9bd3f7385603acdf")
                        .addTextPara("com", "ems")
                        .addTextPara("nu", logistic)
                        .addTextPara("phone",phone.substring(7))
                        .post();
                return res;
            }else {
                String res = new ShowApiRequest("http://route.showapi.com/64-19", "691709", "dafba4849ef343fd9bd3f7385603acdf")
                        .addTextPara("com", "auto")
                        .addTextPara("nu", logistic)
                        .addTextPara("phone",phone.substring(7))
                        .post();
                return res;
            }

    }

    //再买一次
    @Override
    public int BuyItAgain(Integer productId, Integer accountId, Integer receiverId, Integer purchaseOrderProductPnID, Integer FinishedGoodsQty, Integer purchaseOrderITotalPrice, String remarks, Integer totalPoints, double price,String id) {
        OrderCodeFactory orderCodeFactory = new OrderCodeFactory();
        String returnCode = orderCodeFactory.getReturnCode(accountId);
        String orderCode = orderCodeFactory.getOrderCode(accountId);
        PurchaseOrderTb purchaseOrderTb = new PurchaseOrderTb();
        ProductTb productTb = new ProductTb();
        ProductTb orders = productTbMapper.s(productId);
//        String purchaseOrderNumber = purchaseOrderTbMapper.purchaseOrderNumber();
        PersonRegisterTb personRegisterTb = personRegisterTbService.getById(accountId);
        ReceiverTb receiverTb = receiverTbService.getById(receiverId);
//        int result = Integer.valueOf(purchaseOrderNumber.substring(purchaseOrderNumber.length() - 5));
//        int num = result + 1;
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String purchaseOrderNumber1 = Lqi + df.format(time) + num;
        String Lqi = personRegisterTb.getAccountCode();
        if (Lqi.equals("LQI")){
            purchaseOrderTb.setPurchaseOrderNumber(orderCode);
        }if (Lqi.equals("LQC")){
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
        purchaseOrderTb.setOrderHeader(orders.getPurchaseOrderProductName());
        purchaseOrderTb.setAccountId(accountId);
        purchaseOrderTb.setPurchaseOrderIdTotalPrice(purchaseOrderITotalPrice);
        purchaseOrderTb.setPayTime(df2.format(time));
        purchaseOrderTb.setOrderType("待支付");
        purchaseOrderTb.setRemarks(remarks);
        purchaseOrderTb.setStatus(0);
        purchaseOrderTb.setDeal("待处理");
        purchaseOrderTb.setTotalPoints(totalPoints);
        purchaseOrderTb.setYuanDing(null);
        purchaseOrderTbService.save(purchaseOrderTb);
        //添加到物品清单
        if (Lqi.equals("LQI")){
            productTb.setPurchaseOrderNumber(orderCode);
        }if (Lqi.equals("LQC")){
            productTb.setPurchaseOrderNumber(returnCode);
        }
        productTb.setPurchaseOrderProductPn(orders.getPurchaseOrderProductPn());
        productTb.setPurchaseOrderProductPnId(orders.getPurchaseOrderProductPnId());
        productTb.setPurchaseOrderProductName(orders.getPurchaseOrderProductName());
        productTb.setPurchaseOrderProductTotalQty(FinishedGoodsQty);
        productTb.setPrinterModelRequestCustomer(orders.getPrinterModelRequestCustomer());
        productTb.setFactoryMaterialName(orders.getFactoryMaterialName());
        productTb.setColorRequestCustomer(orders.getColorRequestCustomer());
        productTb.setProductLengthRequestCustomer(orders.getProductLengthRequestCustomer());
        productTb.setProductWidthRequestCustomer(orders.getProductWidthRequestCustomer());
        productTb.setProductHeightRequestCustomer(orders.getProductHeightRequestCustomer());
        productTb.setProductAccRequestCustomer(orders.getProductAccRequestCustomer());
        productTb.setProductWeightRequestCustomer(orders.getProductWeightRequestCustomer());
        productTb.setFactoryMaterialPn(orders.getFactoryMaterialPn());
        productTb.setShopAddr(orders.getShopAddr());
//        productTb.setUploadAddr(orders.getUploadAddr());
        productTb.setHomeAddr(orders.getHomeAddr());
        productTb.setDescription(orders.getDescription());
//        productTb.setDiyComment(orders.getDiyComment());
        productTb.setPrice(price);
        productTb.setDownloadAddr(null);
        productTb.setLogistic(null);
        productTb.setLogisticName(null);
        productTbService.save(productTb);
        int id1 = productTbMapper.productId();
        List<BuyNowImgTb> buyNowImgTbs = buyNowImgTbMapper.allImg(productId);
        BuyNowImgTb buyNowImgTb = new BuyNowImgTb();
        if (buyNowImgTbs.size()==0) {
            int cartId = productTbMapper.cartId(productId);
            List<ProductImgTb> productImgTbs = productImgTbMapper.allImg(cartId);
            for (ProductImgTb p : productImgTbs) {
                buyNowImgTb.setProductId(id1);
                buyNowImgTb.setProductImg(p.getProductImg());
                buyNowImgTb.setProductBz(p.getCommentBz());
                buyNowImgTbService.save(buyNowImgTb);
            }
        } else {
            for (BuyNowImgTb b : buyNowImgTbs) {
                buyNowImgTb.setProductId(id1);
                buyNowImgTb.setProductImg(b.getProductImg());
                buyNowImgTb.setProductBz(b.getProductBz());
                buyNowImgTbService.save(buyNowImgTb);
            }
        }
        return purchaseOrderTb.getPurchaseOrderNumberId();
    }

    //取消订单
    @Override
    public void delete(String purchaseOrderNumber) {
        productTbMapper.deleteProduct(purchaseOrderNumber);
        purchaseOrderTbMapper.deletePurchaseOrder(purchaseOrderNumber);
    }

    @Override
    public List<ProductTb> ListProduct(String purchaseOrderNumber) {
        return productTbMapper.allProduct(purchaseOrderNumber);
    }

    //物品信息
    @Override
    public ProductTb productTb(Integer productId) {
        return productTbMapper.productTb(productId);
    }

    //添加快递单号
    @Override
    public void insertLogistic(String logistic, Integer productId) {
        Date time = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        productTbMapper.updateLogistic(logistic, productId,df2.format(time));
    }

    //上传客户自定义物品3D文档到服务器
    @Override
    public void DiyUpload(MultipartFile multipartFile, Integer productId) {
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            productTbMapper.updateDownloadAddr(photoPath, productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查看处理完成的订单物品详情
    @Override
    public List<ProductTb> addDealProduct(String purchaseOrderNumber) {
        return productTbMapper.addDealProduct(purchaseOrderNumber);
    }

    //查看处理完成的订单物品
    @Override
    public List<ProductTb> allDeal() {
        return productTbMapper.deal();
    }

    //查看物流中的物品
    @Override
    public List<ProductTb> addDeal() {
        return productTbMapper.addDeal();
    }

    //查看所有订单的单个物品总数
    @Override
    public String num(String purchaseOrderProductName) {
        if (productTbMapper.num(purchaseOrderProductName) == null) {
            return "0";
        } else {
            return productTbMapper.num(purchaseOrderProductName);
        }
    }

    //已购买过的物品名称
    @Override
    public List<ProductTb> purchaseOrderProductName() {
        return productTbMapper.purchaseOrderProductName();
    }

    //售后详细信息
    @Override
    public ProductTb selectAll(Integer productId) {
        return productTbMapper.selectAll(productId);
    }
}
