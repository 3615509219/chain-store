package com.lq.dao;

import com.lq.pojo.CategoriesTb;
import com.lq.pojo.CategoryTb;
import com.lq.pojo.FinishedGoodsListTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface FinishedGoodsListTbMapper extends BaseMapper<FinishedGoodsListTb> {

    @Select("select * from finished_goods_list_tb where finished_goods_description like '%${finishedGoodsDescription}%' order by bz desc")
    List<FinishedGoodsListTb> getVague(String finishedGoodsDescription);

    @Select("select finished_goods_weight from finished_goods_list_tb where purchase_order_product_pn = '${ItemNo}'")
    Double weight (String ItemNo);

    @Select("select finished_goods_weight from finished_goods_list_sub_tb where purchase_order_product_pn = '${ItemNo}'")
    Double weight1 (String ItemNo);

    @Select("select * from finished_goods_list_tb where account_ID = ${accountId}")
    List<FinishedGoodsListTb> numMoXing (Integer accountId);

    @Select("select * from finished_goods_list_tb where finished_goods_patent = '是' order by bz desc")
    List<FinishedGoodsListTb> getPatent();

    @Select("select * from categories_tb order by categories_sort")
    List<CategoriesTb> getCategory();

    @Select("select * from finished_goods_list_tb t join category_tb c on t.purchase_order_product_pn_ID = c.purchase_order_product_pn_ID where c.finished_goods_category = '${finishedGoodsCategory}' order by bz desc")
    List<FinishedGoodsListTb> getGoodsCategory(String finishedGoodsCategory);

    @Select("select * from finished_goods_list_tb t join category_tb c on t.purchase_order_product_pn_ID = c.purchase_order_product_pn_ID where t.finished_goods_patent = '是' and c.finished_goods_category = '${finishedGoodsCategory}'")
    List<FinishedGoodsListTb> getPatentCategory(String finishedGoodsCategory);

    @Select("select * from finished_goods_list_tb where account_ID = ${accountId} order by xia_jia desc limit 0,${num}")
    List<FinishedGoodsListTb> getAccountLimit(Integer accountId ,Integer num);

    @Select("select * from finished_goods_list_tb where account_ID = ${accountId} order by xia_jia desc")
    List<FinishedGoodsListTb> getAccount(Integer accountId);

    @Select("select * from finished_goods_list_tb where purchase_order_product_pn_ID =${purchaseOrderProductPnId} ")
    FinishedGoodsListTb selectFinishedAccountId (Integer purchaseOrderProductPnId);
//    void insertFinished();

    @Update("update finished_goods_list_tb set finished_goods_print_times = ${finishedGoodsPrintTimes} where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    void updateTimes (Integer finishedGoodsPrintTimes, Integer purchaseOrderProductPnId);

    @Select("select finished_goods_print_times from finished_goods_list_tb where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    int selectTimes (Integer purchaseOrderProductPnId);

    @Update("update finished_goods_list_tb set drawing_files_addr = '${drawingFilesAddr}' where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    void updateUploadFinished (String drawingFilesAddr,Integer purchaseOrderProductPnId);

    @Update("update finished_goods_list_tb set home_page_addr = '${homePageAddr}' where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    void updateHomeImg (String homePageAddr,Integer purchaseOrderProductPnId);

    @Select("select finished_goods_customer_priced , finished_goods_weight from finished_goods_list_tb where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    List<FinishedGoodsListTb> price (Integer purchaseOrderProductPnId);

    @Select("select * from finished_goods_list_tb where purchase_order_product_pn = '${purchaseOrderProductPn}'")
    FinishedGoodsListTb customerPrice (String purchaseOrderProductPn);

    @Update("update finished_goods_list_tb set xia_jia = 1 where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    void t (Integer purchaseOrderProductPnId);

    @Update("update finished_goods_list_tb set xia_jia = 0 where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    void t1 (Integer purchaseOrderProductPnId);

    @Update("update finished_goods_list_tb set bz = ${bz} where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    void updateBz(Integer purchaseOrderProductPnId,Integer bz);

    @Select("\n" +
            "SELECT * FROM finished_goods_list_tb t  WHERE EXISTS (\n" +
            "  SELECT 1 FROM finished_goods_list_tb join category_tb c on t.purchase_order_product_pn_ID = c.purchase_order_product_pn_ID  GROUP BY finished_goods_name\n" +
            "  HAVING MAX(c.purchase_order_product_pn_ID) = t.purchase_order_product_pn_ID) order by bz desc ")
    List<FinishedGoodsListTb> selectAll();

    @Select("select purchase_order_product_pn_ID from finished_goods_list_tb order by purchase_order_product_pn_ID desc limit 1")
    int zuiXing ();

    @Select("select purchase_order_product_pn from finished_goods_list_tb")
    List<FinishedGoodsListTb> pn ();

    @Update("update finished_goods_list_tb set popup_img = '{popupImg}' where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    void updatePopupImg (String popupImg,Integer purchaseOrderProductPnId);

    @Select("select popup_img from finished_goods_list_tb where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    String selectPopupImg (Integer purchaseOrderProductPnId);

}
