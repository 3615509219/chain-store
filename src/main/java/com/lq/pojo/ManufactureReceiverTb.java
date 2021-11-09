package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-04-07
 */
public class ManufactureReceiverTb implements Serializable {


    private static final long serialVersionUID = 8337961032579964391L;
    @TableId(value = "manufacture_receiver_ID", type = IdType.AUTO)
    private Integer manufactureReceiverId;

    @TableField("manufacture_ID")
    private Integer manufactureId;

    private String manufactureReceiverName;

    private String manufactureReceiverPhone;

    private String manufactureReceiverAddr;

    private String manufactureDetailsAddr;

    public String getManufactureDetailsAddr() {
        return manufactureDetailsAddr;
    }

    public void setManufactureDetailsAddr(String manufactureDetailsAddr) {
        this.manufactureDetailsAddr = manufactureDetailsAddr;
    }

    public Integer getManufactureReceiverId() {
        return manufactureReceiverId;
    }

    public void setManufactureReceiverId(Integer manufactureReceiverId) {
        this.manufactureReceiverId = manufactureReceiverId;
    }

    public Integer getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(Integer manufactureId) {
        this.manufactureId = manufactureId;
    }

    public String getManufactureReceiverName() {
        return manufactureReceiverName;
    }

    public void setManufactureReceiverName(String manufactureReceiverName) {
        this.manufactureReceiverName = manufactureReceiverName;
    }

    public String getManufactureReceiverPhone() {
        return manufactureReceiverPhone;
    }

    public void setManufactureReceiverPhone(String manufactureReceiverPhone) {
        this.manufactureReceiverPhone = manufactureReceiverPhone;
    }

    public String getManufactureReceiverAddr() {
        return manufactureReceiverAddr;
    }

    public void setManufactureReceiverAddr(String manufactureReceiverAddr) {
        this.manufactureReceiverAddr = manufactureReceiverAddr;
    }

    @Override
    public String toString() {
        return "ManufactureReceiverTb{" +
        "manufactureReceiverId=" + manufactureReceiverId +
        ", manufactureId=" + manufactureId +
        ", manufactureReceiverName=" + manufactureReceiverName +
        ", manufactureReceiverPhone=" + manufactureReceiverPhone +
        ", manufactureReceiverAddr=" + manufactureReceiverAddr +
        "}";
    }
}
