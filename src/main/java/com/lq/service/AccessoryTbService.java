package com.lq.service;

import com.lq.pojo.AccessoryTb;
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
public interface AccessoryTbService extends IService<AccessoryTb> {

    //获取商城打印机配件
    List<AccessoryTb> allAccessory();
}
