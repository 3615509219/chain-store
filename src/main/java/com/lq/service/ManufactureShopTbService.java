package com.lq.service;

import com.lq.pojo.ManufactureShopTb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-04-15
 */
public interface ManufactureShopTbService extends IService<ManufactureShopTb> {

    //查看分类商城
    List<ManufactureShopTb> categoryShop (String category);

    //查看单个物品
    ManufactureShopTb oneShop (String shopPn);

}
