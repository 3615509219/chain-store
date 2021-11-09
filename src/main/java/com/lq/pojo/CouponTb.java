package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-09-01
 */
@Data
public class CouponTb implements Serializable {


    private static final long serialVersionUID = -4989470001564523591L;
    @TableId(value = "coupon_Id", type = IdType.AUTO)
    private Integer couponId;

    @TableField("account_Id")
    private Integer accountId;

    private double couponPrice;

    private Integer bz;

    private String startTime;

    private String endTime;


    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public double getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(double couponPrice) {
        this.couponPrice = couponPrice;
    }

    @Override
    public String toString() {
        return "CouponTb{" +
        "couponId=" + couponId +
        ", accountId=" + accountId +
        ", couponPrice=" + couponPrice +
        "}";
    }
}
