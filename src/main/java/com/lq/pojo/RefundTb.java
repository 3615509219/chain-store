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
 * @since 2021-06-25
 */
@Data
public class RefundTb implements Serializable {


    private static final long serialVersionUID = -4150996745747123361L;
    @TableId(value = "refund_Id", type = IdType.AUTO)
    private Integer refundId;

    @TableField("account_Id")
    private Integer accountId;

    private String refundApplicationTime;

    private String refundApplicationCompletedTime;

    private String purchaseOrderNumber;

    @TableField("product_Id")
    private Integer productId;

    private String purchaseOrderProductPn;

    private String purchaseOrderProductName;

    private Integer jiFen;

    @TableField("references_Id")
    private Integer referencesId;

    private String reasonsForApplication;

    private String applicationCategory;

    private String applicationReasonDescription;

    private String applicationStatus;

    private String bz;


    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getRefundApplicationTime() {
        return refundApplicationTime;
    }

    public void setRefundApplicationTime(String refundApplicationTime) {
        this.refundApplicationTime = refundApplicationTime;
    }

    public String getRefundApplicationCompletedTime() {
        return refundApplicationCompletedTime;
    }

    public void setRefundApplicationCompletedTime(String refundApplicationCompletedTime) {
        this.refundApplicationCompletedTime = refundApplicationCompletedTime;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPurchaseOrderProductPn() {
        return purchaseOrderProductPn;
    }

    public void setPurchaseOrderProductPn(String purchaseOrderProductPn) {
        this.purchaseOrderProductPn = purchaseOrderProductPn;
    }

    public String getPurchaseOrderProductName() {
        return purchaseOrderProductName;
    }

    public void setPurchaseOrderProductName(String purchaseOrderProductName) {
        this.purchaseOrderProductName = purchaseOrderProductName;
    }

    public Integer getJiFen() {
        return jiFen;
    }

    public void setJiFen(Integer jiFen) {
        this.jiFen = jiFen;
    }

    public Integer getReferencesId() {
        return referencesId;
    }

    public void setReferencesId(Integer referencesId) {
        this.referencesId = referencesId;
    }

    public String getReasonsForApplication() {
        return reasonsForApplication;
    }

    public void setReasonsForApplication(String reasonsForApplication) {
        this.reasonsForApplication = reasonsForApplication;
    }

    public String getApplicationCategory() {
        return applicationCategory;
    }

    public void setApplicationCategory(String applicationCategory) {
        this.applicationCategory = applicationCategory;
    }

    public String getApplicationReasonDescription() {
        return applicationReasonDescription;
    }

    public void setApplicationReasonDescription(String applicationReasonDescription) {
        this.applicationReasonDescription = applicationReasonDescription;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    @Override
    public String toString() {
        return "RefundTb{" +
        "refundId=" + refundId +
        ", accountId=" + accountId +
        ", refundApplicationTime=" + refundApplicationTime +
        ", refundApplicationCompletedTime=" + refundApplicationCompletedTime +
        ", purchaseOrderNumber=" + purchaseOrderNumber +
        ", productId=" + productId +
        ", purchaseOrderProductPn=" + purchaseOrderProductPn +
        ", purchaseOrderProductName=" + purchaseOrderProductName +
        ", jiFen=" + jiFen +
        ", referencesId=" + referencesId +
        ", reasonsForApplication=" + reasonsForApplication +
        ", applicationCategory=" + applicationCategory +
        ", applicationReasonDescription=" + applicationReasonDescription +
        ", applicationStatus=" + applicationStatus +
        "}";
    }
}
