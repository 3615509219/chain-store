package com.lq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lq.pojo.ReceiverTb;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author tooLate
 * @since 2021-01-14
 */
public interface ReceiverTbService extends IService<ReceiverTb> {

    //获取用户收件人信息
    List<ReceiverTb> getAllReceiver(Integer accountId);

    //增加收件人信息
    void addReceiver(ReceiverTb receiverTb);

    //删除收件人信息
    void delReceiver(Integer receiverId);

    //修改收件人信息
    void updateReceiver(ReceiverTb receiverTb);

    //获取一个地址
    ReceiverTb receiver(Integer accountId ,Integer receiverId);

    //设置默认地址
    void updateMr (Integer receiverId,Integer accountId);

    ReceiverTb mrReceiver (Integer accountId);

}
