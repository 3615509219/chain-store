package com.lq.dao;

import com.lq.pojo.ManufactureShopTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-04-15
 */
public interface ManufactureShopTbMapper extends BaseMapper<ManufactureShopTb> {

    @Select("select * from manufacture_shop_tb where category = '${category}'")
    List<ManufactureShopTb> categoryShop (String category);

    @Select("select * from  manufacture_shop_tb where shop_pn = '${shopPn}'")
    ManufactureShopTb oneShop (String shopPn);

}
