package com.lq.dao;

import com.lq.pojo.CartImgTb;
import com.lq.pojo.CustomerCartTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
public interface CustomerCartTbMapper extends BaseMapper<CustomerCartTb> {

    List<CustomerCartTb> selectCart(Integer accountId);

    @Update("update customer_cart_tb set finished_goods_status = 1 where customer_cart_ID = ${customerCartId} and account_ID = ${accountId}")
    void delCart(Integer customerCartId, Integer accountId);

    @Select("select * from customer_cart_tb where customer_cart_ID = ${customerCartId} and account_ID = ${accountId}")
    CustomerCartTb CustomerCartTb(Integer customerCartId, Integer accountId);

    @Update("update customer_cart_tb set finished_goods_qty = ${finishedGoodsQty} where customer_cart_ID = ${customerCartId} and account_ID = ${accountId}")
    void updateQty(Integer finishedGoodsQty, Integer customerCartId, Integer accountId);

    @Update("update customer_cart_tb set finished_goods_cart_date = '${time}' where customer_cart_ID = ${customerCartId} and account_ID = ${accountId}")
    void updateTime(String time, Integer customerCartId, Integer accountId);

    @Update("update customer_cart_tb set select_status = 1 where customer_cart_ID = ${customerCartId}")
    void updateChoose(Integer customerCartId);

    @Update("update customer_cart_tb set select_status = 0 where customer_cart_ID = ${customerCartId}")
    void updateCancelChoose(Integer customerCartId);

    @Update("update customer_cart_tb set select_status = 1 where account_ID = ${accountId}")
    void updateAllChoose(Integer accountId);

    @Update("update customer_cart_tb set select_status = 0 where account_ID = ${accountId}")
    void updateCancelAllChoose(Integer accountId);

    List<CustomerCartTb> getAccountCart(Integer accountId);

    @Update("update customer_cart_tb set integral = ${integral} where customer_cart_ID = ${customerCartId}")
    void updateIntegral (Integer integral,Integer customerCartId);
    @Update("update customer_cart_tb set unit_price = ${unitPrice} where purchase_order_product_pn_ID = ${purchaseOrderProductPnID}")
    void updatePrice (double unitPrice,Integer purchaseOrderProductPnID);

    @Delete("delete from customer_cart_tb where purchase_order_product_pn_ID = ${purchaseOrderProductPnID}")
    void delCart1 (Integer purchaseOrderProductPnID);

    @Select("select * from customer_cart_tb where purchase_order_product_pn = '${purchaseOrderProductPn}' and bz = 0 and finished_goods_status = 0 and account_ID = ${accountId}")
    CustomerCartTb selectBz (String purchaseOrderProductPn,Integer accountId);

    @Update("update customer_cart_tb set finished_goods_qty =${finishedGoodsQty} where customer_cart_ID = ${customerCartTb} ")
    void qty (Integer finishedGoodsQty,Integer customerCartTb);

    @Select("select customer_cart_ID from customer_cart_tb order by customer_cart_ID desc limit 1")
    int customerCartID();

    @Select("select * from customer_cart_tb where finished_goods_list_sub_ID = ${finishedGoodsListSubId} and bz = 0 and finished_goods_status = 0 and account_ID = ${accountId}")
    CustomerCartTb selectBz1 (Integer finishedGoodsListSubId,Integer accountId);
}
