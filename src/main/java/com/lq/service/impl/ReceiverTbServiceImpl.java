package com.lq.service.impl;

import com.lq.pojo.ReceiverTb;
import com.lq.dao.ReceiverTbMapper;
import com.lq.service.ReceiverTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-01-14
 */
@Service
public class ReceiverTbServiceImpl extends ServiceImpl<ReceiverTbMapper, ReceiverTb> implements ReceiverTbService {

    @Resource
    private ReceiverTbService receiverTbService;

    @Resource
    private ReceiverTbMapper receiverTbMapper;
    //获取用户收件人信息
    @Override
    public List<ReceiverTb> getAllReceiver(Integer accountId) {
        return receiverTbMapper.getReceiverTb(accountId);
    }

    //增加收件人信息
    @Override
    public void addReceiver(ReceiverTb receiverTb) {
        receiverTbService.save(receiverTb);
    }

    //删除收件人信息
    @Override
    public void delReceiver(Integer receiverId) {
        receiverTbService.removeById(receiverId);
    }

    //修改收件人信息
    @Override
    public void updateReceiver(ReceiverTb receiverTb) {
        receiverTbMapper.updateRegisterTb(receiverTb);
    }
    //获取一个地址
    @Override
    public ReceiverTb receiver(Integer accountId, Integer receiverId) {
        return receiverTbMapper.re(accountId, receiverId);
    }

    //设置默认地址
    @Override
    public void updateMr(Integer receiverId,Integer accountId) {
        ReceiverTb mr = receiverTbMapper.mrReceiver(accountId);
        receiverTbMapper.updateMr(0,mr.getReceiverId());
        receiverTbMapper.updateMr(1,receiverId);
    }

    //获取默认地址
    @Override
    public ReceiverTb mrReceiver(Integer accountId) {
        return receiverTbMapper.mrReceiver(accountId);
    }
}
