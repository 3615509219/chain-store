package com.lq.service;

import com.lq.pojo.OffTheShelfTb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-11
 */
public interface OffTheShelfTbService extends IService<OffTheShelfTb> {

    //申请下架
    void applyForRemoval (Integer accountId,Integer purchaseOrderProductPnId,String purchaseOrderProductPn,String finishedGoodsName,String reason);

    //处理下架
    void handle (Integer shelfId);

    //填写下架原因
    void updateReasonsForRemoval (String ReasonsForRemoval,Integer shelfId);
}
