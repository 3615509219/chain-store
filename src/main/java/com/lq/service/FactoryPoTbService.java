package com.lq.service;

import com.lq.pojo.FactoryPoTb;
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
public interface FactoryPoTbService extends IService<FactoryPoTb> {

    //生成订单
    int addFactoryPo(Integer manufactureId, Integer manufactureReceiverId,Integer factoryPrice);

    //待发货
    List<FactoryPoTb> ToBeDelivered (Integer manufactureId);
    //已发货
    List<FactoryPoTb> Delivered (Integer manufactureId);
    //待支付
    List<FactoryPoTb> ToBePaid (Integer manufactureId);
}
