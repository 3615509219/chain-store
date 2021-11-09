package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-09-15
 */
@Data
public class CustomOrderTb implements Serializable {

    private static final long serialVersionUID = 1L;

    private String purchaseOrderNumber;

    @TableId(value = "custom_order_Id", type = IdType.AUTO)
    private Integer customOrderId;

    private String purchaseOrderFrom;

    private String purchaseOrderCustomerContactWindow;

    private String purchaseOrderCustomerContactWindowPhone;

    private String purchaseOrderCustomerShippingAddr;

    private String purchaseOrderCustomerShippingAddrContactWindow;

    private String purchaseOrderCustomerShippingAddrContactWindowPhone;

    private String purchaseOrderCustomerOrderDate;

    private String purchaseOrderSalesNames;

    private String purchaseOrderSalesPhone;

    private String orderHeader;

    @TableField("account_Id")
    private Integer accountId;

    private Integer advanceCharge;

    @TableField("purchase_order_ID_total_price")
    private Integer purchaseOrderIdTotalPrice;

    private String confirmationTime;
    private String payTime;

    private String orderType;

    private String remarks;

    private Integer status;

    private String deal;

    private Integer totalPoints;

    private String yuanDing;

    @TableField("coupon_Id")
    private Integer couponId;

    private Double coupon;

    private Double cowMuchCoupon;

    private String moXing;
    private List<ProductTb> productTb;

    private List<CustomOrderUploadTb> customOrderUploadTb;


    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public Integer getCustomOrderId() {
        return customOrderId;
    }

    public void setCustomOrderId(Integer customOrderId) {
        this.customOrderId = customOrderId;
    }

    public String getPurchaseOrderFrom() {
        return purchaseOrderFrom;
    }

    public void setPurchaseOrderFrom(String purchaseOrderFrom) {
        this.purchaseOrderFrom = purchaseOrderFrom;
    }

    public String getPurchaseOrderCustomerContactWindow() {
        return purchaseOrderCustomerContactWindow;
    }

    public void setPurchaseOrderCustomerContactWindow(String purchaseOrderCustomerContactWindow) {
        this.purchaseOrderCustomerContactWindow = purchaseOrderCustomerContactWindow;
    }

    public String getPurchaseOrderCustomerContactWindowPhone() {
        return purchaseOrderCustomerContactWindowPhone;
    }

    public void setPurchaseOrderCustomerContactWindowPhone(String purchaseOrderCustomerContactWindowPhone) {
        this.purchaseOrderCustomerContactWindowPhone = purchaseOrderCustomerContactWindowPhone;
    }

    public String getPurchaseOrderCustomerShippingAddr() {
        return purchaseOrderCustomerShippingAddr;
    }

    public void setPurchaseOrderCustomerShippingAddr(String purchaseOrderCustomerShippingAddr) {
        this.purchaseOrderCustomerShippingAddr = purchaseOrderCustomerShippingAddr;
    }

    public String getPurchaseOrderCustomerShippingAddrContactWindow() {
        return purchaseOrderCustomerShippingAddrContactWindow;
    }

    public void setPurchaseOrderCustomerShippingAddrContactWindow(String purchaseOrderCustomerShippingAddrContactWindow) {
        this.purchaseOrderCustomerShippingAddrContactWindow = purchaseOrderCustomerShippingAddrContactWindow;
    }

    public String getPurchaseOrderCustomerShippingAddrContactWindowPhone() {
        return purchaseOrderCustomerShippingAddrContactWindowPhone;
    }

    public void setPurchaseOrderCustomerShippingAddrContactWindowPhone(String purchaseOrderCustomerShippingAddrContactWindowPhone) {
        this.purchaseOrderCustomerShippingAddrContactWindowPhone = purchaseOrderCustomerShippingAddrContactWindowPhone;
    }

    public String getPurchaseOrderCustomerOrderDate() {
        return purchaseOrderCustomerOrderDate;
    }

    public void setPurchaseOrderCustomerOrderDate(String purchaseOrderCustomerOrderDate) {
        this.purchaseOrderCustomerOrderDate = purchaseOrderCustomerOrderDate;
    }

    public String getPurchaseOrderSalesNames() {
        return purchaseOrderSalesNames;
    }

    public void setPurchaseOrderSalesNames(String purchaseOrderSalesNames) {
        this.purchaseOrderSalesNames = purchaseOrderSalesNames;
    }

    public String getPurchaseOrderSalesPhone() {
        return purchaseOrderSalesPhone;
    }

    public void setPurchaseOrderSalesPhone(String purchaseOrderSalesPhone) {
        this.purchaseOrderSalesPhone = purchaseOrderSalesPhone;
    }

    public String getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(String orderHeader) {
        this.orderHeader = orderHeader;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAdvanceCharge() {
        return advanceCharge;
    }

    public void setAdvanceCharge(Integer advanceCharge) {
        this.advanceCharge = advanceCharge;
    }

    public Integer getPurchaseOrderIdTotalPrice() {
        return purchaseOrderIdTotalPrice;
    }

    public void setPurchaseOrderIdTotalPrice(Integer purchaseOrderIdTotalPrice) {
        this.purchaseOrderIdTotalPrice = purchaseOrderIdTotalPrice;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getYuanDing() {
        return yuanDing;
    }

    public void setYuanDing(String yuanDing) {
        this.yuanDing = yuanDing;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Double getCoupon() {
        return coupon;
    }

    public void setCoupon(Double coupon) {
        this.coupon = coupon;
    }

    @Override
    public String toString() {
        return "CustomOrderTb{" +
        "purchaseOrderNumber=" + purchaseOrderNumber +
        ", customOrderId=" + customOrderId +
        ", purchaseOrderFrom=" + purchaseOrderFrom +
        ", purchaseOrderCustomerContactWindow=" + purchaseOrderCustomerContactWindow +
        ", purchaseOrderCustomerContactWindowPhone=" + purchaseOrderCustomerContactWindowPhone +
        ", purchaseOrderCustomerShippingAddr=" + purchaseOrderCustomerShippingAddr +
        ", purchaseOrderCustomerShippingAddrContactWindow=" + purchaseOrderCustomerShippingAddrContactWindow +
        ", purchaseOrderCustomerShippingAddrContactWindowPhone=" + purchaseOrderCustomerShippingAddrContactWindowPhone +
        ", purchaseOrderCustomerOrderDate=" + purchaseOrderCustomerOrderDate +
        ", purchaseOrderSalesNames=" + purchaseOrderSalesNames +
        ", purchaseOrderSalesPhone=" + purchaseOrderSalesPhone +
        ", orderHeader=" + orderHeader +
        ", accountId=" + accountId +
        ", advanceCharge=" + advanceCharge +
        ", purchaseOrderIdTotalPrice=" + purchaseOrderIdTotalPrice +
        ", payTime=" + payTime +
        ", orderType=" + orderType +
        ", remarks=" + remarks +
        ", status=" + status +
        ", deal=" + deal +
        ", totalPoints=" + totalPoints +
        ", yuanDing=" + yuanDing +
        ", couponId=" + couponId +
        ", coupon=" + coupon +
        "}";
    }
}
