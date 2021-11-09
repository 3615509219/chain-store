package com.lq.dao;

import com.lq.pojo.ImageTb;
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
 * @since 2021-01-21
 */
public interface ImageTbMapper extends BaseMapper<ImageTb> {

    //图片放的地方
    @Select("select drawing_image_addr from image_tb where purchase_order_product_pn_ID = ${purchaseOrderProductPnId} and shop_image = '${shopImage}'")
    List<ImageTb> imageAddr (Integer purchaseOrderProductPnId, String shopImage);

    //查询这个商品的图片
    @Select("select * from image_tb where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    List<ImageTb> geiImage (Integer purchaseOrderProductPnId);

    @Update("update image_tb set drawing_image_addr = '${drawingImageAddr}' where shop_image = '页面说明'")
    void updateImage (String drawingImageAddr);
}
