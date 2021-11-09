package com.lq.service.impl;

import com.lq.pojo.WarehouseNumber;
import com.lq.pojo.WarehouseTb;
import com.lq.dao.WarehouseTbMapper;
import com.lq.service.WarehouseTbService;
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
public class WarehouseTbServiceImpl extends ServiceImpl<WarehouseTbMapper, WarehouseTb> implements WarehouseTbService {

    @Resource
    private WarehouseTbMapper warehouseTbMapper;
    //查看库存
    @Override
    public List<WarehouseTb> selectWarehouse(Integer manufactureId, String category) {
        return warehouseTbMapper.selectCategory(manufactureId, category);
    }

    //查看所有库存
    @Override
    public List<WarehouseTb> allWarehouse(Integer manufactureId) {
        return warehouseTbMapper.allWarehouse(manufactureId);
    }
    //分类
    @Override
    public List<WarehouseTb> allCategory() {
        return warehouseTbMapper.allCategory();
    }
    //总数
    @Override
    public List<WarehouseNumber> sumNumber(String category) {
        return warehouseTbMapper.sumNumber(category);
    }
}
