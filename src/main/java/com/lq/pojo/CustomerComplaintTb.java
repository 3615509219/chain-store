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
 * @since 2021-01-13
 */
//客户投诉表
public class CustomerComplaintTb implements Serializable {

    private static final long serialVersionUID = -1357155485265562326L;
    @TableId(value = "complaint_id", type = IdType.AUTO)
    private Integer complaintId;

    @TableField("account_Code")
    private String accountCode;

    private String purchaseOrderNumber;

    private String complaintCategory;

    private LocalDateTime complaintDate;

    private String complaintContent;


    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getComplaintCategory() {
        return complaintCategory;
    }

    public void setComplaintCategory(String complaintCategory) {
        this.complaintCategory = complaintCategory;
    }

    public LocalDateTime getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(LocalDateTime complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    @Override
    public String toString() {
        return "CustomerComplaintTb{" +
        "complaintId=" + complaintId +
        ", accountCode=" + accountCode +
        ", purchaseOrderNumber=" + purchaseOrderNumber +
        ", complaintCategory=" + complaintCategory +
        ", complaintDate=" + complaintDate +
        ", complaintContent=" + complaintContent +
        "}";
    }
}
