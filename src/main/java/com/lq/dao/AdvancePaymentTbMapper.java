package com.lq.dao;

import com.lq.pojo.AdvancePaymentTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.pojo.CustomOrderTb;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-09-18
 */
public interface AdvancePaymentTbMapper extends BaseMapper<AdvancePaymentTb> {
    @Update("update advance_payment_tb set deal = '支付完成',call_back_date = '${date}' where advance_payment_order = '${orderNum}'")
    void updateDeal (String date ,String orderNum);

    @Select("select * from advance_payment_tb where deal = '${deal}'")
    List<AdvancePaymentTb> PrepaidHospitalityPayment (String deal);

    List<AdvancePaymentTb> PrepaidHospitalityPaymentAccountId (Integer accountId,String deal);

    List<AdvancePaymentTb> yufukuan (String advancePaymentOrder);

    @Select("select * from advance_payment_tb where account_Id = ${accountId}")
    List<AdvancePaymentTb> accountYufukuan (Integer accountId);

    AdvancePaymentTb pppp (Integer Id);

    @Select("select * from advance_payment_tb where deal ='${deal}' order by advance_payment_date DESC")
    List<AdvancePaymentTb> dealYuFuKuan(String deal);

    @Select("select * from custom_order_tb where mo_xing = '${moXing}'")
    List<CustomOrderTb> moXing (String moXing);


    List<AdvancePaymentTb> daiQueRenWu();

    @Select("select * from advance_payment_tb")
    List<AdvancePaymentTb> yufukuan();


    @Select("select * from advance_payment_tb where purchase_order_number = '${purchaseOrderNumber}'")
    List<AdvancePaymentTb> a(String purchaseOrderNumber);
}
