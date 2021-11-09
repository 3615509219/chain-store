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
 * @since 2021-09-18
 */

@Data
public class AdvancePaymentTb implements Serializable {


    private static final long serialVersionUID = 7354370618813940732L;
    @TableId(value = "advance_payment_Id", type = IdType.AUTO)
    private Integer advancePaymentId;

    private String advancePaymentOrder;

    private String orderHeader;

    @TableField("account_Id")
    private Integer accountId;

    private String purchaseOrderNumber;

    private Integer price;

    private String deal;

    private String advancePaymentDate;

    private String CallBackDate;

    private List<CustomOrderTb> customOrderTb;

    private List<CustomOrderUploadTb> customOrderUploadTb;

    public Integer getAdvancePaymentId() {
        return advancePaymentId;
    }

    public void setAdvancePaymentId(Integer advancePaymentId) {
        this.advancePaymentId = advancePaymentId;
    }

    public String getAdvancePaymentOrder() {
        return advancePaymentOrder;
    }

    public void setAdvancePaymentOrder(String advancePaymentOrder) {
        this.advancePaymentOrder = advancePaymentOrder;
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

    @Override
    public String toString() {
        return "AdvancePaymentTb{" +
        "advancePaymentId=" + advancePaymentId +
        ", advancePaymentOrder=" + advancePaymentOrder +
        ", orderHeader=" + orderHeader +
        ", accountId=" + accountId +
        "}";
    }
}
