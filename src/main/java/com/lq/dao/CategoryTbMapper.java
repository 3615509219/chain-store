package com.lq.dao;

import com.lq.pojo.CategoryTb;
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
 * @since 2021-05-21
 */
public interface CategoryTbMapper extends BaseMapper<CategoryTb> {

    @Select("select * from category_tb where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    List<CategoryTb> getCategory (Integer purchaseOrderProductPnId);

    @Update("update category_tb set finished_goods_category = '${gaiCategory}' where finished_goods_category = '${yuanCategory}'")
    void updateCategory(String gaiCategory,String yuanCategory);

    @Delete("delete from category_tb where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    void delCategory (Integer purchaseOrderProductPnId);

    @Select("select count(*) from category_tb where finished_goods_category = '隐藏'")
    Integer yingCang ();
}
