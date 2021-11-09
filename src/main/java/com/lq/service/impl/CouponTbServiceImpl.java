package com.lq.service.impl;

import com.lq.pojo.CouponTb;
import com.lq.dao.CouponTbMapper;
import com.lq.service.CouponTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-09-01
 */
@Service
public class CouponTbServiceImpl extends ServiceImpl<CouponTbMapper, CouponTb> implements CouponTbService {

    @Resource
    private CouponTbMapper couponTbMapper;
    @Resource
    private CouponTbService couponTbService;
    //添加优惠卷
    @Override
    public void addCoupon(Integer accountId, double couponPrice, String endTime,String starTime) {
        Date time=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd "+"00:00:00");
        CouponTb couponTb = new CouponTb();
        couponTb.setAccountId(accountId);
        couponTb.setCouponPrice(couponPrice);
        couponTb.setBz(0);
        couponTb.setStartTime(starTime+" 00:00:00");
        couponTb.setEndTime(endTime+" 23:59:59");
        couponTbMapper.insert(couponTb);
    }

    //删除优惠卷
    @Override
    public void delCoupon(Integer couponId) {
        couponTbMapper.delCoupon(couponId);
    }
    //查看全部优惠卷
    @Override
    public List<CouponTb> allCoupon() {
        return couponTbService.list();
    }
    //查看某个人的优惠卷
    @Override
    public List<CouponTb> accountIdCoupon(Integer accountId) {
        return couponTbMapper.accountIdCoupon(accountId);
    }
    //开始某个时间段之间的优惠卷
    @Override
    public List<CouponTb> timeCoupon(String startTime,String startTime1) {
        return couponTbMapper.startTimeCoupon(startTime, startTime1);
    }
    //查看全部优惠卷多少金额
    @Override
    public List<CouponTb> allCouponPrice(double couponPrice) {
        return couponTbMapper.allPriceCoupon(couponPrice);
    }
    //查看某个人的优惠卷多少金额
    @Override
    public List<CouponTb> accountIdCouponPrice(Integer accountId, double couponPrice) {
        return couponTbMapper.allAccountIdPriceCoupon(accountId, couponPrice);
    }
    //开始某个时间段之间的优惠卷多少金额
    @Override
    public List<CouponTb> timeCouponPrice(String startTime,String startTime1, double couponPrice) {
        return couponTbMapper.startTimePriceCoupon(startTime, startTime1, couponPrice);
    }
    //截至某个时间段之间的优惠卷
    @Override
    public List<CouponTb> endTimeCoupon(String endTime,String endTime1) {
        return couponTbMapper.endTimeCoupon(endTime, endTime1);
    }
    //截至某个时间段之间的优惠卷多少金额
    @Override
    public List<CouponTb> endTimeCouponPrice(String endTime,String endTime1, double couponPrice) {
        return couponTbMapper.endTimePriceCoupon(endTime, endTime1, couponPrice);
    }
}
