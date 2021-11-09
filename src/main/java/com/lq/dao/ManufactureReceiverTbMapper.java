package com.lq.dao;

import com.lq.pojo.ManufactureReceiverTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.pojo.ReceiverTb;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-04-07
 */
public interface ManufactureReceiverTbMapper extends BaseMapper<ManufactureReceiverTb> {

    @Select("select * from manufacture_receiver_tb where manufacture_ID = ${manufactureId}")
    List<ManufactureReceiverTb> getManufactureReceiverTb(Integer manufactureId);

    void updateManufactureRegisterTb (ManufactureReceiverTb manufactureReceiverTb);

    @Select("select * from manufacture_receiver_tb where manufacture_ID = ${manufactureId} and manufacture_receiver_ID = ${manufactureReceiverId}")
    ManufactureReceiverTb oneReceiver(Integer manufactureReceiverId ,Integer manufactureId);

}
