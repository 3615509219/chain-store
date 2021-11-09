package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author tooLate
 * @since 2021-02-04
 */
@Data
public class UploadTb implements Serializable {


    private static final long serialVersionUID = -3805205222749567077L;
    @TableId(value = "upload_ID", type = IdType.AUTO)
    private Integer uploadId;

    @TableField("account_ID")
    private Integer accountId;

    @TableField("manufacture_ID")
    private Integer manufactureId;

    private String uploadAddr;

    private String uploadTime;

    private String description;

    private String patent;

    private String buyNow;

    private String title;

    private String approveStatus;

    private String fail;

    private String time;

    private Integer integral;

    private String state;

    public Integer getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(Integer manufactureId) {
        this.manufactureId = manufactureId;
    }

    private List<PersonRegisterTb> personRegisterTb;

    public List<PersonRegisterTb> getPersonRegisterTb() {
        return personRegisterTb;
    }

    public void setPersonRegisterTb(List<PersonRegisterTb> personRegisterTb) {
        this.personRegisterTb = personRegisterTb;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

    public String getBuyNow() {
        return buyNow;
    }

    public void setBuyNow(String buyNow) {
        this.buyNow = buyNow;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public Integer getUploadId() {
        return uploadId;
    }

    public void setUploadId(Integer uploadId) {
        this.uploadId = uploadId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUploadAddr() {
        return uploadAddr;
    }

    public void setUploadAddr(String uploadAddr) {
        this.uploadAddr = uploadAddr;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UploadTb{" +
                "uploadId=" + uploadId +
                ", accountId=" + accountId +
                ", uploadAddr='" + uploadAddr + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                ", description='" + description + '\'' +
                ", patent='" + patent + '\'' +
                '}';
    }
}
