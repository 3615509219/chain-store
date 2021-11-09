package com.lq.service;

import com.lq.pojo.CustomerCartTb;
import com.lq.pojo.ManufactureCartTb;
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
public interface ManufactureCartTbService extends IService<ManufactureCartTb> {

    //获取购物车
    List<ManufactureCartTb> allCart (Integer manufactureId);

    //删除购物车
    void delCart (Integer manufactureCartId,Integer manufactureId);

    //减少购物车数量
    void reduceManufactureCart(Integer manufactureCartId,Integer manufactureId);

    //增加购物车数量
    void addManufactureCart(Integer manufactureCartId,Integer manufactureId);

    void choose(Integer manufactureCartId);
    //取消选择物品
    void cancelChoose(Integer manufactureCartId);
    //全选物品
    void allChoose(Integer manufactureId);
    //取消全选物品
    void cancelAllChoose(Integer manufactureId);
    //打勾的物品
    List<ManufactureCartTb> getStatus(Integer manufactureId);

    //加入购物车
    void manufactureCart (Integer manufactureId , Integer manufactureCartId,Integer number,Integer status);
}
