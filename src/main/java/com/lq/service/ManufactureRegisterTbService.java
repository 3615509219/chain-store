package com.lq.service;

import com.lq.pojo.ManufactureReceiverTb;
import com.lq.pojo.ManufactureRegisterTb;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lq.pojo.PersonRegisterTb;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
public interface ManufactureRegisterTbService extends IService<ManufactureRegisterTb> {

    //完善工厂信息
    void addManufacture (ManufactureRegisterTb manufactureRegisterTb);

    //修改工厂信息
    void updateManufactureRegister(ManufactureRegisterTb manufactureRegisterTb);

    //验证用户OpenID
    ManufactureRegisterTb getManufactureOpenId(String manufactureOpenId);
}
