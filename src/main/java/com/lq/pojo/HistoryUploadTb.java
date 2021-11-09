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
 * @since 2021-03-29
 */
@Data
public class HistoryUploadTb implements Serializable {


    private static final long serialVersionUID = -3740237583579972005L;
    @TableId(value = "history_upload_Id", type = IdType.AUTO)
    private Integer historyUploadId;

    @TableField("account_ID")
    private Integer accountId;

    @TableField("manufacture_ID")
    private Integer manufactureId;

    private String uploadAddr;

    private String uploadTime;

    private String description;

    private String title;

    private String approveStatus;

    public Integer getHistoryUploadId() {
        return historyUploadId;
    }

    public void setHistoryUploadId(Integer historyUploadId) {
        this.historyUploadId = historyUploadId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(Integer manufactureId) {
        this.manufactureId = manufactureId;
    }

    public String getUploadAddr() {
        return uploadAddr;
    }

    public void setUploadAddr(String uploadAddr) {
        this.uploadAddr = uploadAddr;
    }

    @Override
    public String toString() {
        return "HistoryUploadTb{" +
        "historyUploadId=" + historyUploadId +
        ", accountId=" + accountId +
        ", manufactureId=" + manufactureId +
        ", uploadAddr=" + uploadAddr +
        "}";
    }
}
