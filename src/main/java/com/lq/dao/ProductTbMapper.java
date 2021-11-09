package com.lq.dao;

import com.lq.pojo.ProductTb;
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
public interface ProductTbMapper extends BaseMapper<ProductTb> {

    @Update("update product_tb set purchase_order_product_total_qty = ${qty} where purchase_order_number = '${purchaseOrderNumber}'")
    void updateQty (Integer qty ,String purchaseOrderNumber);

    @Select("select shop_addr from product_tb where purchase_order_number = '${purchaseOrderNumber}'")
    String shopAddr (String purchaseOrderNumber);

    @Update("update product_tb set shop_addr = '${shopAddr}' where purchase_order_number = '${purchaseOrderNumber}'")
    void updateAddr(String shopAddr,String purchaseOrderNumber);

    @Select("select purchase_order_product_total_qty from product_tb where purchase_order_number = '${purchaseOrderNumber}'")
    int qty (String purchaseOrderNumber);

    ProductTb phone1 (String logistic);

    ProductTb phone0 (String logistic);

    List<ProductTb> all(String purchaseOrderNumber);

    @Select("select * from product_tb where purchase_order_number = '${purchaseOrderNumber}'")
    List<ProductTb> product(String purchaseOrderNumber);

    List<ProductTb> undone(Integer accountID);

    List<ProductTb> completed(Integer accountID);

    ProductTb s(Integer productId);

    @Delete("delete from product_tb where purchase_order_number = '${purchaseOrderNumber}'")
     void deleteProduct (String purchaseOrderNumber);

    List<ProductTb> allProduct(String purchaseOrderNumber);

    ProductTb productTb (Integer productId);

    ProductTb selectAll (Integer productId);

    @Update("update product_tb set logistic = '${logistic}',logistic_time = '${logisticTime}'  where product_Id = ${productId}")
    void updateLogistic (String logistic, Integer productId,String logisticTime);

    @Update("update product_tb set download_addr = '${downloadAddr}' where product_Id =${productId}")
    void updateDownloadAddr (String downloadAddr,Integer productId);

    //查看处理完成订单物品详情
    @Select("select * from product_tb where purchase_order_number ='${purchaseOrderNumber}'")
    List<ProductTb> addDealProduct(String purchaseOrderNumber);

    List<ProductTb> deal();

    List<ProductTb> touBuDeal(String touBu);

    List<ProductTb> addDeal();

    List<ProductTb> touBuAddDeal(String touBu,String purchaseOrderProductName);

    @Select("select sum(purchase_order_product_total_qty) as 物品总数 from product_tb where logistic is not null and purchase_order_product_name = '${purchaseOrderProductName}'")
    String num (String purchaseOrderProductName);

    @Select("select DISTINCT purchase_order_product_name from product_tb where logistic is not null")
    List<ProductTb> purchaseOrderProductName ();

    @Select("select product_Id from product_tb order by product_Id desc limit 1")
    int productId ();

    List<ProductTb> daiZhiFu (String purchaseOrderNumber);

    List<ProductTb> daiZhiFu2 (String purchaseOrderNumber);

    List<ProductTb> weiWanCheng (Integer productId);

    List<ProductTb> weiWanCheng2 (Integer productId);

    List<ProductTb> yiWanCheng (Integer productId);

    List<ProductTb> yiWanCheng2 (Integer productId);

    @Select("select customer_cart_ID from product_tb where product_Id = ${productId}")
    int cartId (Integer productId);

    @Update("update product_tb set shou_hou = '无法申请售后' where product_Id =${productId}")
    void shouHou (Integer productId);

}
