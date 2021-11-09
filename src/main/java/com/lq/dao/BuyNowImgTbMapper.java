package com.lq.dao;

import com.lq.pojo.BuyNowImgTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-05-31
 */
public interface BuyNowImgTbMapper extends BaseMapper<BuyNowImgTb> {

    @Select("select * from buy_now_img_tb where product_Id = ${productId}")
    List<BuyNowImgTb> allImg(Integer productId);
}
