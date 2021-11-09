package com.lq.dao;

import com.lq.pojo.ManufactureReceiverTb;
import com.lq.pojo.ManufactureRegisterTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.pojo.PersonRegisterTb;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
public interface ManufactureRegisterTbMapper extends BaseMapper<ManufactureRegisterTb> {

    //修改工厂信息
    void updateManufacture (ManufactureRegisterTb manufactureRegisterTb);

    @Select("select * from manufacture_register_tb where manufacture_open_id = '${manufactureOpenId}'")
    ManufactureRegisterTb getManufactureOpenId(String manufactureOpenId);
}
