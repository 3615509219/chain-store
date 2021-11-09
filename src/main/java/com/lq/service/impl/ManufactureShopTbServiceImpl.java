package com.lq.service.impl;

import com.lq.pojo.ManufactureShopTb;
import com.lq.dao.ManufactureShopTbMapper;
import com.lq.service.ManufactureShopTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-04-15
 */
@Service
public class ManufactureShopTbServiceImpl extends ServiceImpl<ManufactureShopTbMapper, ManufactureShopTb> implements ManufactureShopTbService {

    @Resource
    private ManufactureShopTbMapper manufactureShopTbMapper;
    //查看分类商城
    @Override
    public List<ManufactureShopTb> categoryShop(String category) {
        return manufactureShopTbMapper.categoryShop(category);
    }

    //查看单个物品
    @Override
    public ManufactureShopTb oneShop(String shopPn) {
        return manufactureShopTbMapper.oneShop(shopPn);
    }
}
