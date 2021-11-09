package com.lq.service;

import com.lq.pojo.HistoryFinishedGoodsListTb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-11
 */
public interface HistoryFinishedGoodsListTbService extends IService<HistoryFinishedGoodsListTb> {

    //下架
    void OffTheShelf (Integer purchaseOrderProductPnId);

    //取消下架或者再次上架
    int noTheShelf (Integer purchaseOrderProductPnId,Integer id);

}
