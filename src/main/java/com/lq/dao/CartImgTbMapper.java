package com.lq.dao;

import com.lq.pojo.CartImgTb;
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
public interface CartImgTbMapper extends BaseMapper<CartImgTb> {

    @Select("select ci.* from customer_cart_tb cc\n" +
            "        join cart_img_tb ci\n" +
            "        on cc.customer_cart_ID = ci.customer_cart_ID\n" +
            "        where cc.account_ID = #{accountId,jdbcType=INTEGER}\n" +
            "        and cc.finished_goods_status = 0\n" +
            "        and cc.select_status = 1\n" +
            "        order by cc.customer_cart_ID desc")
    List<CartImgTb> getStatus(Integer accountId);

}
