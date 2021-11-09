package com.lq.service;

import com.lq.pojo.CustomOrderTb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-09-15
 */
public interface CustomOrderTbService extends IService<CustomOrderTb> {

    //生成待确认订单
    int orderTbc (Integer accountId,Integer id,Integer totalPoints,String code,String moXing);
}
