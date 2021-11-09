package com.lq.service;

import com.lq.pojo.CouponTb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-09-01
 */
public interface CouponTbService extends IService<CouponTb> {

    //添加优惠卷
    void addCoupon (Integer accountId,double couponPrice,String endTime,String starTime);

    //删除优惠卷
    void delCoupon (Integer couponId);

    //查看全部优惠卷
    List<CouponTb> allCoupon ();

    //查看某个人的优惠卷
    List<CouponTb> accountIdCoupon (Integer accountId);

    //开始某个时间段之间的优惠卷
    List<CouponTb> timeCoupon(String startTime,String startTime1);

    //查看全部优惠卷多少金额
    List<CouponTb> allCouponPrice (double couponPrice);

    //查看某个人的优惠卷多少金额
    List<CouponTb> accountIdCouponPrice (Integer accountId, double couponPrice);

    //开始某个时间段之间的优惠卷多少金额
    List<CouponTb> timeCouponPrice(String startTime,String startTime1,double couponPrice);

    //截至某个时间段之间的优惠卷
    List<CouponTb> endTimeCoupon(String endTime,String endTime1);
    //截至某个时间段之间的优惠卷多少金额
    List<CouponTb> endTimeCouponPrice(String endTime,String endTime1,double couponPrice);
}
