package com.lq.service;

import com.lq.pojo.CategoryTb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-21
 */
public interface CategoryTbService extends IService<CategoryTb> {


    String addCategory (Integer purchaseOrderProductPnId, String finishedGoodsCategory);

    List<CategoryTb> getCategory (Integer purchaseOrderProductPnId);
}
