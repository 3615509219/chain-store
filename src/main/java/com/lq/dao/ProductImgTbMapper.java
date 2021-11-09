package com.lq.dao;

import com.lq.pojo.ProductImgTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-05-25
 */
public interface ProductImgTbMapper extends BaseMapper<ProductImgTb> {

    @Select("select * from product_img_tb where customer_cart_ID = ${customerCartId}")
    List<ProductImgTb> allImg(Integer customerCartId);

}
