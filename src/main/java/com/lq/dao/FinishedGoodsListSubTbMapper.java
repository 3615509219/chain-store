package com.lq.dao;

import com.lq.pojo.FinishedGoodsListSubTb;
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
 * @since 2021-08-26
 */
public interface FinishedGoodsListSubTbMapper extends BaseMapper<FinishedGoodsListSubTb> {
    @Select("select * from finished_goods_list_sub_tb where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    List<FinishedGoodsListSubTb> selectSub (Integer purchaseOrderProductPnId);

    @Select("select * from finished_goods_list_sub_tb where finished_goods_list_sub_ID = ${finishedGoodsListSubId}")
    FinishedGoodsListSubTb f (Integer finishedGoodsListSubId);

    @Update("update finished_goods_list_sub_tb set drawing_files_addr = '${drawingFilesAddr}' where finished_goods_list_sub_ID = ${finishedGoodsListSubId}")
    void updateDrawingFilesAddr (String drawingFilesAddr , Integer finishedGoodsListSubId);

    @Update("update finished_goods_list_sub_tb set home_page_addr = '${homePageAddr}' where finished_goods_list_sub_ID = ${finishedGoodsListSubId}")
    void updateHomePageAddr (String homePageAddr, Integer finishedGoodsListSubId);

    @Update("update finished_goods_list_sub_tb set drawing_files_addr = '${drawingFilesAddr}' where finished_goods_list_sub_ID = ${finishedGoodsListSubId}")
    void updateUploadFinishedSub (String drawingFilesAddr,Integer finishedGoodsListSubId);

    @Update("update finished_goods_list_sub_tb set home_page_addr = '${homePageAddr}' where finished_goods_list_sub_ID = ${finishedGoodsListSubId}")
    void updateHomeImgSub (String homePageAddr,Integer finishedGoodsListSubId);
}
