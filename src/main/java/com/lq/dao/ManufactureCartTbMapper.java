package com.lq.dao;

import com.lq.pojo.CustomerCartTb;
import com.lq.pojo.ManufactureCartTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
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
public interface ManufactureCartTbMapper extends BaseMapper<ManufactureCartTb> {

    @Select("select * from manufacture_cart_tb where manufacture_ID = ${manufactureId}")
    List<ManufactureCartTb> allCart(Integer manufactureId);

    @Delete("delete from manufacture_cart_tb where manufacture_cart_Id = ${manufactureCartId}  and manufacture_ID = ${manufactureId}")
    void delCart(Integer manufactureCartId,Integer manufactureId);

    @Select("select * from manufacture_cart_tb where manufacture_cart_Id = ${manufactureCartId} and manufacture_ID = ${manufactureId}")
    ManufactureCartTb allManufactureCart(Integer manufactureCartId, Integer manufactureId);

    @Update("update manufacture_cart_tb set number = ${number} where manufacture_cart_Id = ${manufactureCartId} and manufacture_ID = ${manufactureId}")
    void updateCartNum(Integer number, Integer manufactureCartId, Integer manufactureId);

    @Update("update manufacture_cart_tb set status = 1 where manufacture_cart_Id = ${manufactureCartId}")
    void updateChoose(Integer manufactureCartId);

    @Update("update manufacture_cart_tb set status = 0 where manufacture_cart_Id = ${manufactureCartId}")
    void updateCancelChoose(Integer manufactureCartId);

    @Update("update manufacture_cart_tb set status = 1 where manufacture_ID = ${manufactureId}")
    void updateAllChoose(Integer manufactureId);

    @Update("update manufacture_cart_tb set status = 0 where manufacture_ID = ${manufactureId}")
    void updateCancelAllChoose(Integer manufactureId);

    @Select("select * from manufacture_cart_tb where status = 1 and manufacture_ID = ${manufactureId}")
    List<ManufactureCartTb> getAccountCart(Integer manufactureId);
}
