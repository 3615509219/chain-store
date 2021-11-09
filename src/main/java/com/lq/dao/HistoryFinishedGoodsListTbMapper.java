package com.lq.dao;

import com.lq.pojo.HistoryFinishedGoodsListTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-05-11
 */
public interface HistoryFinishedGoodsListTbMapper extends BaseMapper<HistoryFinishedGoodsListTb> {


    @Select("select * from history_finished_goods_list_tb where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    HistoryFinishedGoodsListTb s (Integer purchaseOrderProductPnId);
    @Delete("delete from history_finished_goods_list_tb where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    void del (Integer purchaseOrderProductPnId);
}
