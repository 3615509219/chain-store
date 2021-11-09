package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
//订单表
@Data
public class PurchaseOrderTb implements Serializable {

    private static final long serialVersionUID = 7702546934846843602L;
    private String purchaseOrderNumber;

    @TableId(value = "purchase_order_number_ID", type = IdType.AUTO)
    private Integer purchaseOrderNumberId;

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

    @TableField("account_ID")
    private int accountId;

    @TableField("purchase_order_ID_total_price")
    private Integer purchaseOrderIdTotalPrice;

    private String payTime;

    private String orderType;

    private String remarks;

    private Integer status;

    private String deal;

    private Integer totalPoints;

    private String yuanDing;

    private Integer couponId;

    private Double coupon;

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(String orderHeader) {
        this.orderHeader = orderHeader;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public Integer getPurchaseOrderNumberId() {
        return purchaseOrderNumberId;
    }

    public void setPurchaseOrderNumberId(Integer purchaseOrderNumberId) {
        this.purchaseOrderNumberId = purchaseOrderNumberId;
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

    public void setPurchaseOrderCustomerContactWindowPhone(String purchaseOrderCustomerConactWindowPhone) {
        this.purchaseOrderCustomerContactWindowPhone = purchaseOrderCustomerConactWindowPhone;
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Integer getPurchaseOrderIdTotalPrice() {
        return purchaseOrderIdTotalPrice;
    }

    public void setPurchaseOrderIdTotalPrice(Integer purchaseOrderIdTotalPrice) {
        this.purchaseOrderIdTotalPrice = purchaseOrderIdTotalPrice;
    }

    @Override
    public String toString() {
        return "PurchaseOrderTb{" +
                "purchaseOrderNumber='" + purchaseOrderNumber + '\'' +
                ", purchaseOrderNumberId=" + purchaseOrderNumberId +
                ", purchaseOrderFrom='" + purchaseOrderFrom + '\'' +
                ", purchaseOrderCustomerContactWindow='" + purchaseOrderCustomerContactWindow + '\'' +
                ", purchaseOrderCustomerConactWindowPhone='" + purchaseOrderCustomerContactWindowPhone + '\'' +
                ", purchaseOrderCustomerShippingAddr='" + purchaseOrderCustomerShippingAddr + '\'' +
                ", purchaseOrderCustomerShippingAddrContactWindow='" + purchaseOrderCustomerShippingAddrContactWindow + '\'' +
                ", purchaseOrderCustomerShippingAddrContactWindowPhone='" + purchaseOrderCustomerShippingAddrContactWindowPhone + '\'' +
                ", purchaseOrderCustomerOrderDate=" + purchaseOrderCustomerOrderDate +
                ", purchaseOrderSalesNames='" + purchaseOrderSalesNames + '\'' +
                ", purchaseOrderSalesPhone='" + purchaseOrderSalesPhone + '\'' +
                ", orderHeader='" + orderHeader + '\'' +
                ", accountId=" + accountId +
                ", purchaseOrderIdTotalPrice=" + purchaseOrderIdTotalPrice +
                ", payTime=" + payTime +
                ", orderType='" + orderType + '\'' +
                '}';
    }
}
