package com.lq.service;

import com.lq.pojo.ManufactureReceiverTb;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lq.pojo.ReceiverTb;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-04-07
 */
public interface ManufactureReceiverTbService extends IService<ManufactureReceiverTb> {
    //获取工厂收件人信息
    List<ManufactureReceiverTb> getAllManufactureReceiver(Integer manufactureId);

    //增加工厂收件人信息
    void addManufactureReceiver(ManufactureReceiverTb manufactureReceiverTb);

    //删除工厂收件人信息
    void delManufactureReceiver(Integer manufactureReceiverId);

    //修改工厂收件人信息
    void updateManufactureReceiver(ManufactureReceiverTb manufactureReceiverTb);

    //获取一个地址
    ManufactureReceiverTb oneReceiver (Integer manufactureReceiverId , Integer manufactureId);


}
