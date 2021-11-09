package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-05-11
 */
@Data
@TableName("Off_the_shelf_tb")
public class OffTheShelfTb implements Serializable {


    private static final long serialVersionUID = 761160450132287225L;
    @TableId(value = "shelf_Id", type = IdType.AUTO)
    private Integer shelfId;

    @TableField("account_ID")
    private Integer accountId;

    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;

    private String purchaseOrderProductPn;

    private String finishedGoodsName;

    private String reason;

    private String applicationTime;

    private String auditTime;

    private String examine;

    private String ReasonsForRemoval;


    public Integer getShelfId() {
        return shelfId;
    }

    public void setShelfId(Integer shelfId) {
        this.shelfId = shelfId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getPurchaseOrderProductPnId() {
        return purchaseOrderProductPnId;
    }

    public void setPurchaseOrderProductPnId(Integer purchaseOrderProductPnId) {
        this.purchaseOrderProductPnId = purchaseOrderProductPnId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    @Override
    public String toString() {
        return "OffTheShelfTb{" +
        "shelfId=" + shelfId +
        ", accountId=" + accountId +
        ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        ", reason=" + reason +
        ", applicationTime=" + applicationTime +
        ", auditTime=" + auditTime +
        ", examine=" + examine +
        "}";
    }
}
