package com.lq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.pojo.PersonRegisterTb;
import com.lq.pojo.UploadTb;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
public interface PersonRegisterTbMapper extends BaseMapper<PersonRegisterTb> {


    @Select("select * from person_register_tb where person_open_id = '${personOpenId}'")
    PersonRegisterTb getPersonOpenId(String personOpenId);

    @Select("select sum(coupon_price) from coupon_tb c join purchase_order_tb p\n" +
            "on c.coupon_Id = p.coupon_Id\n" +
            "where p.order_type = '支付完成' and p.account_Id = ${accountId} and c.bz = 1")
    String patP (Integer accountId);
    @Select("select sum(coupon_price) from coupon_tb c join custom_order_tb p\n" +
            "on c.coupon_Id = p.coupon_Id\n" +
            "where p.order_type = '支付完成' and p.account_Id = ${accountId} and c.bz = 1")
    String patP1 (Integer accountId);

    void updatePerson (PersonRegisterTb personRegisterTb);

    @Update("update person_register_tb set account_points = ${accountPoints} where account_ID = ${accountId}")
    void updatePoints(Integer accountPoints,Integer accountId);

    @Select("select account_points from person_register_tb where account_ID = ${accountId}")
    int getPoints(Integer accountId);

    @Select("select * from person_register_tb where account_ID = ${accountId}")
    PersonRegisterTb person(Integer accountId);

    @Select("select account_ID from person_register_tb")
    List<PersonRegisterTb> selectAccountId();

    @Update("update person_register_tb set account_ID = ${xingAccountId} where account_ID =${laoAccountId}")
    void updateAccountId(Integer xingAccountId,Integer laoAccountId);

    @Select("SELECT sum(purchase_order_ID_total_price) FROM purchase_order_tb where order_type = '支付完成' and account_Id = ${accountId}")
    String payPrice (Integer accountId);

    @Select("SELECT sum(purchase_order_ID_total_price) FROM custom_order_tb where order_type = '支付完成' and account_Id = ${accountId}")
    String pay1Price (Integer accountId);


    @Select("select * from person_register_tb where account_ID = ${accountId}")
    PersonRegisterTb a1 (Integer accountId);
}
