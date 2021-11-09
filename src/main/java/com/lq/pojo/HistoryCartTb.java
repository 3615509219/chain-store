package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-03-16
 */
public class HistoryCartTb implements Serializable {


    private static final long serialVersionUID = 2262889556492059698L;
    @TableId(value = "history_cart_Id", type = IdType.AUTO)
    private Integer historyCartId;

    @TableField("account_ID")
    private Integer accountId;

    private String finishedGoodsName;

    private String purchaseOrderProductPn;

    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;

    private String finishedGoodsDescription;

    private Integer finishedGoodsQty;

    private LocalDateTime finishedGoodsCartDate;

    private Double unitPrice;

    private String uploadAddr;

    private String comment;

    private String purchaseOrder;

    private LocalDateTime orderOpenTime;

    private LocalDateTime orderCloseTime;

    private String logisticNumber;

    private Integer getPoints;

    private LocalDateTime payTime;

    private String receiver;

    private String shippingAddr;

    private String receiverPhone;


    public Integer getHistoryCartId() {
        return historyCartId;
    }

    public void setHistoryCartId(Integer historyCartId) {
        this.historyCartId = historyCartId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getFinishedGoodsName() {
        return finishedGoodsName;
    }

    public void setFinishedGoodsName(String finishedGoodsName) {
        this.finishedGoodsName = finishedGoodsName;
    }

    public String getPurchaseOrderProductPn() {
        return purchaseOrderProductPn;
    }

    public void setPurchaseOrderProductPn(String purchaseOrderProductPn) {
        this.purchaseOrderProductPn = purchaseOrderProductPn;
    }

    public Integer getPurchaseOrderProductPnId() {
        return purchaseOrderProductPnId;
    }

    public void setPurchaseOrderProductPnId(Integer purchaseOrderProductPnId) {
        this.purchaseOrderProductPnId = purchaseOrderProductPnId;
    }

    public String getFinishedGoodsDescription() {
        return finishedGoodsDescription;
    }

    public void setFinishedGoodsDescription(String finishedGoodsDescription) {
        this.finishedGoodsDescription = finishedGoodsDescription;
    }

    public Integer getFinishedGoodsQty() {
        return finishedGoodsQty;
    }

    public void setFinishedGoodsQty(Integer finishedGoodsQty) {
        this.finishedGoodsQty = finishedGoodsQty;
    }

    public LocalDateTime getFinishedGoodsCartDate() {
        return finishedGoodsCartDate;
    }

    public void setFinishedGoodsCartDate(LocalDateTime finishedGoodsCartDate) {
        this.finishedGoodsCartDate = finishedGoodsCartDate;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUploadAddr() {
        return uploadAddr;
    }

    public void setUploadAddr(String uploadAddr) {
        this.uploadAddr = uploadAddr;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public LocalDateTime getOrderOpenTime() {
        return orderOpenTime;
    }

    public void setOrderOpenTime(LocalDateTime orderOpenTime) {
        this.orderOpenTime = orderOpenTime;
    }

    public LocalDateTime getOrderCloseTime() {
        return orderCloseTime;
    }

    public void setOrderCloseTime(LocalDateTime orderCloseTime) {
        this.orderCloseTime = orderCloseTime;
    }

    public String getLogisticNumber() {
        return logisticNumber;
    }

    public void setLogisticNumber(String logisticNumber) {
        this.logisticNumber = logisticNumber;
    }

    public Integer getGetPoints() {
        return getPoints;
    }

    public void setGetPoints(Integer getPoints) {
        this.getPoints = getPoints;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getShippingAddr() {
        return shippingAddr;
    }

    public void setShippingAddr(String shippingAddr) {
        this.shippingAddr = shippingAddr;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    @Override
    public String toString() {
        return "HistoryCartTb{" +
        "historyCartId=" + historyCartId +
        ", accountId=" + accountId +
        ", finishedGoodsName=" + finishedGoodsName +
        ", purchaseOrderProductPn=" + purchaseOrderProductPn +
        ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        ", finishedGoodsDescription=" + finishedGoodsDescription +
        ", finishedGoodsQty=" + finishedGoodsQty +
        ", finishedGoodsCartDate=" + finishedGoodsCartDate +
        ", unitPrice=" + unitPrice +
        ", uploadAddr=" + uploadAddr +
        ", comment=" + comment +
        ", purchaseOrder=" + purchaseOrder +
        ", orderOpenTime=" + orderOpenTime +
        ", orderCloseTime=" + orderCloseTime +
        ", logisticNumber=" + logisticNumber +
        ", getPoints=" + getPoints +
        ", payTime=" + payTime +
        ", receiver=" + receiver +
        ", shippingAddr=" + shippingAddr +
        ", receiverPhone=" + receiverPhone +
        "}";
    }
}
