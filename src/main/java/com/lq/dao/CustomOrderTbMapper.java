package com.lq.dao;

import com.lq.pojo.CustomOrderTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.pojo.ProductTb;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-09-15
 */
public interface CustomOrderTbMapper extends BaseMapper<CustomOrderTb> {

    @Update("update custom_order_tb set order_header = '${orderHeader}',purchase_order_ID_total_price = ${price},total_points = ${points},confirmation_time = '${confirmationTime}' where custom_order_Id = ${Id}")
    void updateTbs (String orderHeader,Integer price,Integer Id,Integer points,String confirmationTime);

    @Select("select * from custom_order_tb where custom_order_Id = ${Id}")
    CustomOrderTb b (Integer Id);

    @Select("select * from custom_order_tb where purchase_order_number = '${purchaseOrderNumber}'")
    CustomOrderTb point (String purchaseOrderNumber);

    @Select("select purchase_order_number from custom_order_tb where custom_order_Id = ${Id}")
    String purchaseOrderNumber1 (Integer Id);

    @Update("update custom_order_tb set order_type = '支付完成',pay_time = '${payTime}' where purchase_order_number = '${purchaseOrderNumber}'")
    void updatePay(String purchaseOrderNumber, String payTime);

    @Update("update custom_order_tb set purchase_order_ID_total_price = ${price},cow_much_coupon = ${cowMuchCoupon},total_points = ${points} where custom_order_Id = ${Id}")
    void updatePrice (Integer price,Integer Id,Double cowMuchCoupon,Integer points);

    @Select("select person_open_id from custom_order_tb co \n" +
            "join person_register_tb pr\n" +
            "on co.account_ID = pr.account_ID\n" +
            "where co.custom_order_Id = ${Id}")
    String openId (Integer Id);

    @Select("select * from custom_order_tb where deal = '待确认'")
    List<CustomOrderTb> selectAll();

    List<CustomOrderTb> selectAccount(Integer accountId);

    @Update("update custom_order_tb set purchase_order_customer_shipping_addr = '${addr}',purchase_order_customer_shipping_addr_contact_window = '${addrContactWindow}',purchase_order_customer_shipping_addr_contact_window_phone = '${addrContactWindowPhone}'where custom_order_Id = ${Id}")
    void paymentByAddress (String addr,String addrContactWindow, String addrContactWindowPhone , Integer Id);
    @Update("update custom_order_tb set coupon = ${coupon} ,coupon_Id = ${couponId} where custom_order_Id = ${Id} ")
    void updateCoupon (Double coupon,Integer couponId,Integer Id);

    List<CustomOrderTb> CustomOrderDetails (String purchaseOrderNumber);

    List<CustomOrderTb> yiWanCheng(Integer accountId);

    List<CustomOrderTb> weiWanCheng(Integer accountId);

    List<CustomOrderTb> pendingPaymentConfirmed (Integer accountId,String orderType,String deal);
    @Select("select * from custom_order_tb where custom_order_Id= ${Id}")
    CustomOrderTb selectP(Integer Id);

    @Select("select * from custom_order_tb where mo_xing = '有' and deal = '待确认'")
    List<CustomOrderTb> daiQueRen ();

    @Update("update custom_order_tb set deal = '已确认' where  purchase_order_number = '${purchaseOrderNumber}'")
    void updateYiQueRen (String purchaseOrderNumber);

    @Select("select * from custom_order_tb where deal = '已确认'")
    List<CustomOrderTb> allYiQueRen();

    @Select("select * from custom_order_tb")
    List<CustomOrderTb> allDingZhi();

    @Select("select * from custom_order_tb where deal ='已确认' and order_type = '待支付'")
    List<CustomOrderTb> ConfirmToBePaid ();

    @Select("select * from custom_order_tb where deal ='已确认' and order_type = '支付完成'")
    List<CustomOrderTb> confirmPaid();

    @Select("select * from product_tb where purchase_order_number = '${purchaseOrderNumber}'")
    List<ProductTb> viewItemDetails (String purchaseOrderNumber);

    List<CustomOrderTb> customizationNotCompleted();

    List<CustomOrderTb> customizationCompleted();

}
