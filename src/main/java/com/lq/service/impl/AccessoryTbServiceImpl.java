package com.lq.service.impl;

import com.lq.pojo.AccessoryTb;
import com.lq.dao.AccessoryTbMapper;
import com.lq.service.AccessoryTbService;
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
 * @since 2021-01-13
 */
@Service
public class AccessoryTbServiceImpl extends ServiceImpl<AccessoryTbMapper, AccessoryTb> implements AccessoryTbService {
    @Resource
    private AccessoryTbService accessoryTbService;

    //获取商城打印机配件
    @Override
    public List<AccessoryTb> allAccessory() {
        return accessoryTbService.list();
    }
}
