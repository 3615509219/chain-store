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
 * @since 2021-09-15
 */
@Data
public class CustomOrderUploadTb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "custom_order_upload_Id", type = IdType.AUTO)
    private Integer customOrderUploadId;

    @TableField("custom_order_Id")
    private Integer customOrderId;

    @TableField("account_Id")
    private Integer accountId;

    private String customOrderUploadAddr;

    private String customOrderUploadName;

    private String customOrderUploadDescribe;

    private String date;


    public Integer getCustomOrderUploadId() {
        return customOrderUploadId;
    }

    public void setCustomOrderUploadId(Integer customOrderUploadId) {
        this.customOrderUploadId = customOrderUploadId;
    }

    public Integer getCustomOrderId() {
        return customOrderId;
    }

    public void setCustomOrderId(Integer customOrderId) {
        this.customOrderId = customOrderId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getCustomOrderUploadAddr() {
        return customOrderUploadAddr;
    }

    public void setCustomOrderUploadAddr(String customOrderUploadAddr) {
        this.customOrderUploadAddr = customOrderUploadAddr;
    }

    public String getCustomOrderUploadName() {
        return customOrderUploadName;
    }

    public void setCustomOrderUploadName(String customOrderUploadName) {
        this.customOrderUploadName = customOrderUploadName;
    }

    public String getCustomOrderUploadDescribe() {
        return customOrderUploadDescribe;
    }

    public void setCustomOrderUploadDescribe(String customOrderUploadDescribe) {
        this.customOrderUploadDescribe = customOrderUploadDescribe;
    }

    @Override
    public String toString() {
        return "CustomOrderUploadTb{" +
        "customOrderUploadId=" + customOrderUploadId +
        ", customOrderId=" + customOrderId +
        ", accountId=" + accountId +
        ", customOrderUploadAddr=" + customOrderUploadAddr +
        ", customOrderUploadName=" + customOrderUploadName +
        ", customOrderUploadDescribe=" + customOrderUploadDescribe +
        "}";
    }
}
