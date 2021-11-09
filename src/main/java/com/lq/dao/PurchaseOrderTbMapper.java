package com.lq.dao;

import com.lq.pojo.CustomOrderTb;
import com.lq.pojo.ProductTb;
import com.lq.pojo.PurchaseOrderTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
public interface PurchaseOrderTbMapper extends BaseMapper<PurchaseOrderTb> {

    @Select("select person_open_id from purchase_order_tb po join person_register_tb pr\n" +
            "on po.account_ID = pr.account_ID\n" +
            "where po.purchase_order_number_ID = ${purchaseOrderNumberID}")
    String personOpenId (Integer purchaseOrderNumberID);
    @Select("SELECT * FROM purchase_order_tb WHERE  pay_time BETWEEN '${congDate}' AND '${daoDate}' and order_type = '${orderType}'")
    List<PurchaseOrderTb> dateOrder (String congDate,String daoDate,String orderType);

    @Select("SELECT * FROM custom_order_tb WHERE  pay_time BETWEEN '${congDate}' AND '${daoDate}' and order_type = '${orderType}'")
    List<CustomOrderTb> CustomDateOrder (String congDate, String daoDate, String orderType);


    @Select("SELECT * FROM custom_order_tb WHERE  purchase_order_customer_order_date BETWEEN '${congDate}' AND '${daoDate}'and deal = '${deal}'")
    List<CustomOrderTb> daiQueRenCustomDateOrder (String congDate, String daoDate,String deal);

    @Update("update purchase_order_tb set pay_time = '${payTime}',order_type = '${orderType}'\n" +
            "where purchase_order_number = '${purchaseOrderNumber}'")
    void updatePay(String purchaseOrderNumber, String orderType, String payTime);

    @Select("select purchase_order_number from purchase_order_tb where purchase_order_number_ID = ${id}")
    String id1 (Integer id);

    @Select("select purchase_order_number from purchase_order_tb order by purchase_order_number_ID desc limit 1")
    String purchaseOrderNumber();

    @Select("select * from  purchase_order_tb where account_ID = ${accountId} and order_type ='待支付' and status = 0 order by purchase_order_number_ID desc;")
    List<PurchaseOrderTb> UnpaidOrders (Integer accountId);

    @Update("update purchase_order_tb set status = 1 where  purchase_order_number = '${purchaseOrderNumber}'")
    void delPurchase(String purchaseOrderNumber);

    @Select("select * from purchase_order_tb po join product_tb p\n" +
            "on po.purchase_order_number = p.purchase_order_number\n" +
            "where p.product_Id = ${productId}")
    ProductTb order(Integer productId);

    @Delete("delete from purchase_order_tb where purchase_order_number = '${purchaseOrderNumber}'")
    void deletePurchaseOrder (String purchaseOrderNumber);

    @Select("select * from purchase_order_tb where order_type = '支付完成' and deal = '待处理'")
    List<PurchaseOrderTb> allPurchase();

    @Select("select * from purchase_order_tb where order_type = '支付完成' and deal = '待处理' and purchase_order_number like '${touBu}%'")
    List<PurchaseOrderTb> touBu(String touBu);

    @Update("update purchase_order_tb set deal = '处理完成' where purchase_order_number = '${purchaseOrderNumber}'")
    void updatePurchase (String purchaseOrderNumber);

    @Update("update purchase_order_tb set purchase_order_customer_shipping_addr = '${purchaseOrderCustomerShippingAddr}',purchase_order_customer_shipping_addr_contact_window='${purchaseOrderCustomerShippingAddrContactWindow}',purchase_order_customer_shipping_addr_contact_window_phone='${purchaseOrderCustomerShippingAddrContactWindowPhone}'where purchase_order_number='${purchaseOrderNumber}'")
    void updateReceiver(String purchaseOrderCustomerShippingAddr,String purchaseOrderCustomerShippingAddrContactWindow,String purchaseOrderCustomerShippingAddrContactWindowPhone,String purchaseOrderNumber);

    @Select("select account_ID from purchase_order_tb where purchase_order_number = '${purchaseOrderNumber}'")
    int selectAccountId(String purchaseOrderNumber);

    @Select("select DISTINCT purchase_order_sales_names from purchase_order_tb where order_type = '支付完成'")
    List<PurchaseOrderTb> id ();

    @Select("select * from purchase_order_tb where purchase_order_sales_names is not null and order_type = '支付完成' order by pay_time desc")
    List<PurchaseOrderTb> yesId();

    @Select("select * from purchase_order_tb where purchase_order_sales_names = '${id}' and order_type = '支付完成' order by pay_time desc")
    List<PurchaseOrderTb> allId(String id);

    @Select("select * from purchase_order_tb where purchase_order_sales_names = '${id}' and purchase_order_sales_phone = '待处理' and order_type = '支付完成' order by pay_time")
    List<PurchaseOrderTb> daiChuLi(String id);

    @Select("select * from purchase_order_tb where purchase_order_sales_names = '${id}' and purchase_order_sales_phone = '已处理' and order_type = '支付完成' order by pay_time desc")
    List<PurchaseOrderTb> yiChuLi(String id);

    @Update("update purchase_order_tb set purchase_order_sales_phone = '已处理' where purchase_order_number_ID = ${id}")
    void updateChuLi(Integer id);

    @Select("select * from purchase_order_tb where purchase_order_sales_phone = '${chulLi}' and order_type = '支付完成'")
    List<PurchaseOrderTb> chuLi (String chuLi);

    @Select("select distinct po.purchase_order_number from purchase_order_tb po join product_tb p\n" +
            "on p.purchase_order_number = po.purchase_order_number\n" +
            "join nfc_tb n\n" +
            "on n.product_Id = p.product_Id\n" +
            "where n.nfc_url is not null")
    List<PurchaseOrderTb> selectPurchaseOrderNumber ();

    @Select("select * from purchase_order_tb where purchase_order_number = '${purchaseOrderNumber}'")
    PurchaseOrderTb r (String purchaseOrderNumber);
}
