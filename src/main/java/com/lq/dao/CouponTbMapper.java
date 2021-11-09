package com.lq.dao;

import com.lq.pojo.CouponTb;
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
 * @since 2021-09-01
 */
public interface CouponTbMapper extends BaseMapper<CouponTb> {

    @Select("select * from coupon_tb where coupon_Id = ${couponId}")
    CouponTb coupon (Integer couponId);

    @Update("update coupon_tb set bz = 1 where coupon_Id = ${couponId}")
    void delCoupon (Integer couponId);

    @Select("select * from coupon_tb where account_Id = ${accountId} and bz = 0")
    List<CouponTb> accountIdCoupon (Integer accountId);

    @Select("SELECT * FROM coupon_tb where start_time Between '${startTime}' And '${startTime1}' and bz = 0")
    List<CouponTb> startTimeCoupon(String startTime,String startTime1);

    @Select("select * from coupon_tb where coupon_price = ${couponPrice} and bz = 0")
    List<CouponTb> allPriceCoupon (double couponPrice);

    @Select("select * from coupon_tb where coupon_price = ${couponPrice} and account_Id = ${accountId} and bz = 0")
    List<CouponTb> allAccountIdPriceCoupon (Integer accountId,double couponPrice);

    @Select("SELECT * FROM coupon_tb where start_time Between '${startTime}' And '${startTime1}' and coupon_price = ${couponPrice} and bz = 0")
    List<CouponTb> startTimePriceCoupon(String startTime,String startTime1,double couponPrice);

    @Select("SELECT * FROM coupon_tb where end_time Between '${endTime}' And '${endTime1}'and bz = 0")
    List<CouponTb> endTimeCoupon (String endTime,String endTime1);


    @Select("SELECT * FROM coupon_tb where end_time Between '${endTime}' And '${endTime1}' and coupon_price = ${couponPrice} and bz = 0")
    List<CouponTb> endTimePriceCoupon(String endTime,String endTime1,double couponPrice);

    @Select("select * from coupon_tb where bz = 0")
    List<CouponTb> selectAll ();

    @Update("update coupon_tb set coupon_price = {price} where coupon_Id = ${couponId}")
    void surplusCoupon (Integer couponId , double price);
}
