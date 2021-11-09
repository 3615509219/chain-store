package com.lq.service;

import com.lq.pojo.WarehouseNumber;
import com.lq.pojo.WarehouseTb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-04-15
 */
public interface WarehouseTbService extends IService<WarehouseTb> {

    //查看库存
    List<WarehouseTb> selectWarehouse(Integer manufactureId , String category);

    //查看所有库存
    List<WarehouseTb> allWarehouse(Integer manufactureId );

    //分类
    List<WarehouseTb> allCategory();

    //总数
    List<WarehouseNumber> sumNumber (String category);
}
