package com.lq.controller;


import com.lq.dao.CouponTbMapper;
import com.lq.pojo.CouponTb;
import com.lq.service.CouponTbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-09-01
 */
@Controller
@RequestMapping("/couponTb")
public class CouponTbController {

    @Resource
    private CouponTbService couponTbService;
    @Resource
    private CouponTbMapper couponTbMapper;

    //查看单个优惠卷的信息
    @GetMapping("oneCouponS")
    @ResponseBody
    public CouponTb oneCouponS (Integer couponId){
        return couponTbMapper.coupon(couponId);
    }
    //添加优惠卷
    @PostMapping("addCouponS")
    @ResponseBody
    public void addCoupon (Integer accountId, double couponPrice, String endTime,String starTime){
        couponTbService.addCoupon(accountId, couponPrice, endTime,starTime);
    }

    //删除优惠卷
    @PostMapping("delCouponS")
    @ResponseBody
    public void delCouponS (Integer couponId){
        couponTbService.delCoupon(couponId);
    }
    //没有用完的优惠卷
    @PostMapping("surplusCouponS")
    @ResponseBody
    public void surplusCouponS (Integer couponId ,Double price){
        CouponTb byId = couponTbService.getById(couponId);
        CouponTb couponTb = new CouponTb();
        couponTb.setAccountId(byId.getAccountId());
        couponTb.setCouponPrice(price);
        couponTb.setBz(0);
        couponTb.setStartTime(byId.getStartTime());
        couponTb.setEndTime(byId.getEndTime());
        couponTbMapper.insert(couponTb);
    }

    //查看全部优惠卷
    @GetMapping("allCouponS")
    @ResponseBody
    public List<CouponTb> allCouponS (){
        return couponTbMapper.selectAll();
    }

    //查看某个人的优惠卷
    @GetMapping("accountIdCouponS")
    @ResponseBody
    public List<CouponTb> accountIdCouponS (Integer accountId){
        return couponTbService.accountIdCoupon(accountId);
    }
    //开始某个时间段之间的优惠卷
    @GetMapping("timeCoupon")
    @ResponseBody
    public List<CouponTb> timeCouponS (String startTime,String startTime1){
        return couponTbService.timeCoupon(startTime, startTime1);
    }

    //查看全部优惠卷多少金额
    @GetMapping("allCouponPriceS")
    @ResponseBody
    public List<CouponTb> allCouponPriceS (double couponPrice){
        return couponTbService.allCouponPrice(couponPrice);
    }

    //查看某个人的优惠卷多少金额
    @GetMapping("accountIdCouponPriceS")
    @ResponseBody
    public List<CouponTb> accountIdCouponPrice (Integer accountId, double couponPrice){
        return couponTbService.accountIdCouponPrice(accountId, couponPrice);
    }

    //开始某个时间段之间的优惠卷多少金额
    @GetMapping("timeCouponPriceS")
    @ResponseBody
    public List<CouponTb> timeCouponPriceS (String startTime,String startTime1, double couponPrice){
        return couponTbService.timeCouponPrice(startTime, startTime1, couponPrice);
    }

    //截至某个时间段之间的优惠卷
    @GetMapping("endTimeCouponS")
    @ResponseBody
    public List<CouponTb> endTimeCouponS (String endTime,String endTime1){
        return couponTbService.endTimeCoupon(endTime, endTime1);
    }

    //截至某个时间段之间的优惠卷多少金额
    @GetMapping("endTimeCouponPriceS")
    @ResponseBody
    public List<CouponTb> endTimeCouponPriceS (String endTime,String endTime1, double couponPrice){
        return couponTbService.endTimeCouponPrice(endTime, endTime1, couponPrice);
    }

}

