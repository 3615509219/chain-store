package com.lq.dao;

import com.lq.pojo.FactoryPoTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-04-15
 */
public interface FactoryPoTbMapper extends BaseMapper<FactoryPoTb> {

    @Select("select factory_Po from factory_Po_tb order by factory_Po_Id desc limit 1")
    String factoryPo ();

    @Select("select manufacture_open_id from factory_Po_tb f join manufacture_register_tb mr \n" +
            "on f.manufacture_ID = mr.manufacture_ID\n" +
            "where f.factory_Po_Id = ${factoryPoId}")
    String manufactureOpenId(Integer factoryPoId);

    @Update("update factory_Po_tb set pay_time = '${payTime}',order_type = '${orderType}'\n" +
            "where factory_Po = '${purchaseOrderNumber}'")
    void updatePay(String purchaseOrderNumber, String orderType, String payTime);


    @Select("select * from factory_Po_tb where logistic is null and order_type = '支付完成' and manufacture_ID = ${manufactureId}")
    List<FactoryPoTb> ToBeDelivered (Integer manufactureId);

    @Select("select * from factory_Po_tb where logistic is not null and order_type = '支付完成' and manufacture_ID = ${manufactureId}")
    List<FactoryPoTb> Delivered (Integer manufactureId);

    @Select("select * from factory_Po_tb where order_type = '待支付' and manufacture_ID = ${manufactureId}")
    List<FactoryPoTb> ToBePaid (Integer manufactureId);
}
