package com.lq.service.impl;

import com.lq.pojo.ManufactureReceiverTb;
import com.lq.dao.ManufactureReceiverTbMapper;
import com.lq.service.ManufactureReceiverTbService;
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
 * @since 2021-04-07
 */
@Service
public class ManufactureReceiverTbServiceImpl extends ServiceImpl<ManufactureReceiverTbMapper, ManufactureReceiverTb> implements ManufactureReceiverTbService {
    @Resource
    private ManufactureReceiverTbService manufactureReceiverTbService;
    @Resource
    private ManufactureReceiverTbMapper manufactureReceiverTbMapper;

    //获取工厂收件人信息
    @Override
    public List<ManufactureReceiverTb> getAllManufactureReceiver(Integer manufactureId) {
        return manufactureReceiverTbMapper.getManufactureReceiverTb(manufactureId);
    }

    //增加工厂收件人信息
    @Override
    public void addManufactureReceiver(ManufactureReceiverTb manufactureReceiverTb) {
        manufactureReceiverTbService.save(manufactureReceiverTb);
    }

    //删除工厂收件人信息
    @Override
    public void delManufactureReceiver(Integer manufactureReceiverId) {
        manufactureReceiverTbService.removeById(manufactureReceiverId);
    }

    //修改工厂收件人信息
    @Override
    public void updateManufactureReceiver(ManufactureReceiverTb manufactureReceiverTb) {
        manufactureReceiverTbMapper.updateManufactureRegisterTb(manufactureReceiverTb);
    }
    //获取一个地址
    @Override
    public ManufactureReceiverTb oneReceiver(Integer manufactureReceiverId, Integer manufactureId) {
        return manufactureReceiverTbMapper.oneReceiver(manufactureReceiverId, manufactureId);
    }
}
