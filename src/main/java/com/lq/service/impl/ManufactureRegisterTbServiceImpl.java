package com.lq.service.impl;

import com.lq.pojo.ManufactureReceiverTb;
import com.lq.pojo.ManufactureRegisterTb;
import com.lq.dao.ManufactureRegisterTbMapper;
import com.lq.service.ManufactureRegisterTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
@Service
public class ManufactureRegisterTbServiceImpl extends ServiceImpl<ManufactureRegisterTbMapper, ManufactureRegisterTb> implements ManufactureRegisterTbService {

    @Resource
    private ManufactureRegisterTbService manufactureRegisterTbService;

    @Resource
    private ManufactureRegisterTbMapper manufactureRegisterTbMapper;
    //完善工厂信息
    @Override
    public void addManufacture(ManufactureRegisterTb manufactureRegisterTb) {
        manufactureRegisterTbService.save(manufactureRegisterTb);
    }
    //修改工厂信息
    @Override
    public void updateManufactureRegister(ManufactureRegisterTb manufactureRegisterTb) {
        manufactureRegisterTbMapper.updateManufacture(manufactureRegisterTb);
    }
    //验证用户OpenID
    @Override
    public ManufactureRegisterTb getManufactureOpenId(String manufactureOpenId) {
        return manufactureRegisterTbMapper.getManufactureOpenId(manufactureOpenId);
    }
}
