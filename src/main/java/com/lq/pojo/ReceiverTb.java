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
 * @since 2021-01-14
 */
//收件人信息

    @Data
public class ReceiverTb implements Serializable {


    private static final long serialVersionUID = 8189475403140406029L;
    @TableId(value = "receiver_ID", type = IdType.AUTO)
    private Integer receiverId;

    @TableField("account_ID")
    private Integer accountId;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddr;

    private String detailsAddr;

    private Integer mr;

    public String getDetailsAddr() {
        return detailsAddr;
    }

    public void setDetailsAddr(String detailsAddr) {
        this.detailsAddr = detailsAddr;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    @Override
    public String toString() {
        return "ReceiverTb{" +
                "receiverId=" + receiverId +
                ", accountId=" + accountId +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverAddr='" + receiverAddr + '\'' +
                ", detailsAddr='" + detailsAddr + '\'' +
                '}';
    }
}
