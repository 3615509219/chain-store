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
//后处理设备表
public class PostDeviceTb implements Serializable {

    private static final long serialVersionUID = 9102049873118669050L;
    @TableId(value = "post_device_ID", type = IdType.AUTO)
    private Integer postDeviceId;

    private String postDevice;

    private String postDeviceVendor;

    @TableField("post_device_IP_addr")
    private String postDeviceIpAddr;

    private String postDeviceModel;

    private Double postDeviceLength;

    private Double postDeviceWidth;

    private Double postDeviceHeight;

    @TableField("post_device_Max_length")
    private Double postDeviceMaxLength;

    @TableField("post_device_Max_width")
    private Double postDeviceMaxWidth;

    @TableField("post_device_Max_height")
    private Double postDeviceMaxHeight;

    private Double postDeviceAcc;

    private Double postDeviceWeight;

    private LocalDateTime postDeviceShippingDate;

    private Integer postDeviceWarranty;

    @TableField("post_device_status_Runing_idle_maintance")
    private String postDeviceStatusRuningIdleMaintance;

    private LocalDateTime postDeviceUsingStartDate;

    private LocalDateTime postDeviceMaintancePlanDate;


    public Integer getPostDeviceId() {
        return postDeviceId;
    }

    public void setPostDeviceId(Integer postDeviceId) {
        this.postDeviceId = postDeviceId;
    }

    public String getPostDevice() {
        return postDevice;
    }

    public void setPostDevice(String postDevice) {
        this.postDevice = postDevice;
    }

    public String getPostDeviceVendor() {
        return postDeviceVendor;
    }

    public void setPostDeviceVendor(String postDeviceVendor) {
        this.postDeviceVendor = postDeviceVendor;
    }

    public String getPostDeviceIpAddr() {
        return postDeviceIpAddr;
    }

    public void setPostDeviceIpAddr(String postDeviceIpAddr) {
        this.postDeviceIpAddr = postDeviceIpAddr;
    }

    public String getPostDeviceModel() {
        return postDeviceModel;
    }

    public void setPostDeviceModel(String postDeviceModel) {
        this.postDeviceModel = postDeviceModel;
    }

    public Double getPostDeviceLength() {
        return postDeviceLength;
    }

    public void setPostDeviceLength(Double postDeviceLength) {
        this.postDeviceLength = postDeviceLength;
    }

    public Double getPostDeviceWidth() {
        return postDeviceWidth;
    }

    public void setPostDeviceWidth(Double postDeviceWidth) {
        this.postDeviceWidth = postDeviceWidth;
    }

    public Double getPostDeviceHeight() {
        return postDeviceHeight;
    }

    public void setPostDeviceHeight(Double postDeviceHeight) {
        this.postDeviceHeight = postDeviceHeight;
    }

    public Double getPostDeviceMaxLength() {
        return postDeviceMaxLength;
    }

    public void setPostDeviceMaxLength(Double postDeviceMaxLength) {
        this.postDeviceMaxLength = postDeviceMaxLength;
    }

    public Double getPostDeviceMaxWidth() {
        return postDeviceMaxWidth;
    }

    public void setPostDeviceMaxWidth(Double postDeviceMaxWidth) {
        this.postDeviceMaxWidth = postDeviceMaxWidth;
    }

    public Double getPostDeviceMaxHeight() {
        return postDeviceMaxHeight;
    }

    public void setPostDeviceMaxHeight(Double postDeviceMaxHeight) {
        this.postDeviceMaxHeight = postDeviceMaxHeight;
    }

    public Double getPostDeviceAcc() {
        return postDeviceAcc;
    }

    public void setPostDeviceAcc(Double postDeviceAcc) {
        this.postDeviceAcc = postDeviceAcc;
    }

    public Double getPostDeviceWeight() {
        return postDeviceWeight;
    }

    public void setPostDeviceWeight(Double postDeviceWeight) {
        this.postDeviceWeight = postDeviceWeight;
    }

    public LocalDateTime getPostDeviceShippingDate() {
        return postDeviceShippingDate;
    }

    public void setPostDeviceShippingDate(LocalDateTime postDeviceShippingDate) {
        this.postDeviceShippingDate = postDeviceShippingDate;
    }

    public Integer getPostDeviceWarranty() {
        return postDeviceWarranty;
    }

    public void setPostDeviceWarranty(Integer postDeviceWarranty) {
        this.postDeviceWarranty = postDeviceWarranty;
    }

    public String getPostDeviceStatusRuningIdleMaintance() {
        return postDeviceStatusRuningIdleMaintance;
    }

    public void setPostDeviceStatusRuningIdleMaintance(String postDeviceStatusRuningIdleMaintance) {
        this.postDeviceStatusRuningIdleMaintance = postDeviceStatusRuningIdleMaintance;
    }

    public LocalDateTime getPostDeviceUsingStartDate() {
        return postDeviceUsingStartDate;
    }

    public void setPostDeviceUsingStartDate(LocalDateTime postDeviceUsingStartDate) {
        this.postDeviceUsingStartDate = postDeviceUsingStartDate;
    }

    public LocalDateTime getPostDeviceMaintancePlanDate() {
        return postDeviceMaintancePlanDate;
    }

    public void setPostDeviceMaintancePlanDate(LocalDateTime postDeviceMaintancePlanDate) {
        this.postDeviceMaintancePlanDate = postDeviceMaintancePlanDate;
    }

    @Override
    public String toString() {
        return "PostDeviceTb{" +
        "postDeviceId=" + postDeviceId +
        ", postDevice=" + postDevice +
        ", postDeviceVendor=" + postDeviceVendor +
        ", postDeviceIpAddr=" + postDeviceIpAddr +
        ", postDeviceModel=" + postDeviceModel +
        ", postDeviceLength=" + postDeviceLength +
        ", postDeviceWidth=" + postDeviceWidth +
        ", postDeviceHeight=" + postDeviceHeight +
        ", postDeviceMaxLength=" + postDeviceMaxLength +
        ", postDeviceMaxWidth=" + postDeviceMaxWidth +
        ", postDeviceMaxHeight=" + postDeviceMaxHeight +
        ", postDeviceAcc=" + postDeviceAcc +
        ", postDeviceWeight=" + postDeviceWeight +
        ", postDeviceShippingDate=" + postDeviceShippingDate +
        ", postDeviceWarranty=" + postDeviceWarranty +
        ", postDeviceStatusRuningIdleMaintance=" + postDeviceStatusRuningIdleMaintance +
        ", postDeviceUsingStartDate=" + postDeviceUsingStartDate +
        ", postDeviceMaintancePlanDate=" + postDeviceMaintancePlanDate +
        "}";
    }
}
