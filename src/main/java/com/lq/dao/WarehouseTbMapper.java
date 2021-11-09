package com.lq.dao;

import com.lq.pojo.WarehouseNumber;
import com.lq.pojo.WarehouseTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-04-15
 */
public interface WarehouseTbMapper extends BaseMapper<WarehouseTb> {

    @Select("select * from warehouse_tb where manufacture_ID = ${manufactureId} and category = '${category}' order by date_of_manufacture asc")
    List<WarehouseTb> selectCategory (Integer manufactureId, String category);

    @Select("select * from warehouse_tb where manufacture_ID = ${manufactureId}")
    List<WarehouseTb> allWarehouse (Integer manufactureId);

    @Select("select distinct category from warehouse_tb")
    List<WarehouseTb> allCategory();

    @Select("select  warehouse_pn , sum(warehouse_number) as qty  from warehouse_tb where category = '${category}'   group by  warehouse_pn")
    List<WarehouseNumber> sumNumber (String category);

}
