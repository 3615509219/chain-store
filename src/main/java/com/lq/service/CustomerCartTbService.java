package com.lq.service;

import com.lq.pojo.CustomerCartTb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
public interface CustomerCartTbService extends IService<CustomerCartTb> {

    //获取购物车
    List<CustomerCartTb> getCart(Integer accountId);
    //删除购物车
    void deiCart (Integer customerCartId ,Integer accountId);
    //物品减少数量
    void reduceCart (Integer customerCartId,Integer accountId);
    //物品增加数量
    void addCart (Integer customerCartId,Integer accountId);
    //添加物品到购物车状态1
    int cart1 (Integer accountId, Integer finishedGoodsQty , Integer purchaseOrderProductPnId,Integer finishedGoodsListSubId, Integer selectStatus, Integer integral, Integer jf);
    //添加物品到购物车状态0
    int cart0 (Integer accountId, Integer finishedGoodsQty , Integer purchaseOrderProductPnId,String purchaseOrderProductPn,Integer finishedGoodsListSubId,Integer selectStatus,Integer integral,Integer jf);
    //添加物品到购物车状态2
    int cart2 (Integer accountId, Integer finishedGoodsQty , Integer purchaseOrderProductPnId,Integer finishedGoodsListSubId,String comment,Integer selectStatus,Integer integral,Integer jf);

    void choose(Integer customerCartId);
    //取消选择物品
    void cancelChoose(Integer customerCartId);
    //全选物品
    void allChoose(Integer accountId);
    //取消全选物品
    void cancelAllChoose(Integer accountId);
    //打勾的物品
    List<CustomerCartTb> getSelectStatus(Integer accountId);

    void updateIntegral (Integer integral,Integer customerCartId);
}
