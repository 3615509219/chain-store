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
//特殊处理设备表
public class SpeicalDeviceTb implements Serializable {

    private static final long serialVersionUID = -1629671816101203803L;
    @TableId(value = "speical_device_ID", type = IdType.AUTO)
    private Integer speicalDeviceId;

    private String speicalDevice;

    private String speicalDeviceVendor;

    @TableField("speical_device_IP_addr")
    private String speicalDeviceIpAddr;

    private String speicalDeviceModel;

    private Double speicalDeviceLength;

    private Double speicalDeviceWidth;

    private Double speicalDeviceHeight;

    @TableField("speical_device_Max_length")
    private Double speicalDeviceMaxLength;

    @TableField("speical_device_Max_width")
    private Double speicalDeviceMaxWidth;

    @TableField("speical_device_Max_height")
    private Double speicalDeviceMaxHeight;

    private Double speicalDeviceAcc;

    private Double speicalDeviceWeight;

    private LocalDateTime speicalDeviceShippingDate;

    private Integer speicalDeviceWarranty;

    @TableField("speical_device_status_Runing_idle_maintance")
    private String speicalDeviceStatusRuningIdleMaintance;

    private LocalDateTime speicalDeviceUsingStartDate;

    private LocalDateTime speicalDeviceMaintancePlanDate;


    public Integer getSpeicalDeviceId() {
        return speicalDeviceId;
    }

    public void setSpeicalDeviceId(Integer speicalDeviceId) {
        this.speicalDeviceId = speicalDeviceId;
    }

    public String getSpeicalDevice() {
        return speicalDevice;
    }

    public void setSpeicalDevice(String speicalDevice) {
        this.speicalDevice = speicalDevice;
    }

    public String getSpeicalDeviceVendor() {
        return speicalDeviceVendor;
    }

    public void setSpeicalDeviceVendor(String speicalDeviceVendor) {
        this.speicalDeviceVendor = speicalDeviceVendor;
    }

    public String getSpeicalDeviceIpAddr() {
        return speicalDeviceIpAddr;
    }

    public void setSpeicalDeviceIpAddr(String speicalDeviceIpAddr) {
        this.speicalDeviceIpAddr = speicalDeviceIpAddr;
    }

    public String getSpeicalDeviceModel() {
        return speicalDeviceModel;
    }

    public void setSpeicalDeviceModel(String speicalDeviceModel) {
        this.speicalDeviceModel = speicalDeviceModel;
    }

    public Double getSpeicalDeviceLength() {
        return speicalDeviceLength;
    }

    public void setSpeicalDeviceLength(Double speicalDeviceLength) {
        this.speicalDeviceLength = speicalDeviceLength;
    }

    public Double getSpeicalDeviceWidth() {
        return speicalDeviceWidth;
    }

    public void setSpeicalDeviceWidth(Double speicalDeviceWidth) {
        this.speicalDeviceWidth = speicalDeviceWidth;
    }

    public Double getSpeicalDeviceHeight() {
        return speicalDeviceHeight;
    }

    public void setSpeicalDeviceHeight(Double speicalDeviceHeight) {
        this.speicalDeviceHeight = speicalDeviceHeight;
    }

    public Double getSpeicalDeviceMaxLength() {
        return speicalDeviceMaxLength;
    }

    public void setSpeicalDeviceMaxLength(Double speicalDeviceMaxLength) {
        this.speicalDeviceMaxLength = speicalDeviceMaxLength;
    }

    public Double getSpeicalDeviceMaxWidth() {
        return speicalDeviceMaxWidth;
    }

    public void setSpeicalDeviceMaxWidth(Double speicalDeviceMaxWidth) {
        this.speicalDeviceMaxWidth = speicalDeviceMaxWidth;
    }

    public Double getSpeicalDeviceMaxHeight() {
        return speicalDeviceMaxHeight;
    }

    public void setSpeicalDeviceMaxHeight(Double speicalDeviceMaxHeight) {
        this.speicalDeviceMaxHeight = speicalDeviceMaxHeight;
    }

    public Double getSpeicalDeviceAcc() {
        return speicalDeviceAcc;
    }

    public void setSpeicalDeviceAcc(Double speicalDeviceAcc) {
        this.speicalDeviceAcc = speicalDeviceAcc;
    }

    public Double getSpeicalDeviceWeight() {
        return speicalDeviceWeight;
    }

    public void setSpeicalDeviceWeight(Double speicalDeviceWeight) {
        this.speicalDeviceWeight = speicalDeviceWeight;
    }

    public LocalDateTime getSpeicalDeviceShippingDate() {
        return speicalDeviceShippingDate;
    }

    public void setSpeicalDeviceShippingDate(LocalDateTime speicalDeviceShippingDate) {
        this.speicalDeviceShippingDate = speicalDeviceShippingDate;
    }

    public Integer getSpeicalDeviceWarranty() {
        return speicalDeviceWarranty;
    }

    public void setSpeicalDeviceWarranty(Integer speicalDeviceWarranty) {
        this.speicalDeviceWarranty = speicalDeviceWarranty;
    }

    public String getSpeicalDeviceStatusRuningIdleMaintance() {
        return speicalDeviceStatusRuningIdleMaintance;
    }

    public void setSpeicalDeviceStatusRuningIdleMaintance(String speicalDeviceStatusRuningIdleMaintance) {
        this.speicalDeviceStatusRuningIdleMaintance = speicalDeviceStatusRuningIdleMaintance;
    }

    public LocalDateTime getSpeicalDeviceUsingStartDate() {
        return speicalDeviceUsingStartDate;
    }

    public void setSpeicalDeviceUsingStartDate(LocalDateTime speicalDeviceUsingStartDate) {
        this.speicalDeviceUsingStartDate = speicalDeviceUsingStartDate;
    }

    public LocalDateTime getSpeicalDeviceMaintancePlanDate() {
        return speicalDeviceMaintancePlanDate;
    }

    public void setSpeicalDeviceMaintancePlanDate(LocalDateTime speicalDeviceMaintancePlanDate) {
        this.speicalDeviceMaintancePlanDate = speicalDeviceMaintancePlanDate;
    }

    @Override
    public String toString() {
        return "SpeicalDeviceTb{" +
        "speicalDeviceId=" + speicalDeviceId +
        ", speicalDevice=" + speicalDevice +
        ", speicalDeviceVendor=" + speicalDeviceVendor +
        ", speicalDeviceIpAddr=" + speicalDeviceIpAddr +
        ", speicalDeviceModel=" + speicalDeviceModel +
        ", speicalDeviceLength=" + speicalDeviceLength +
        ", speicalDeviceWidth=" + speicalDeviceWidth +
        ", speicalDeviceHeight=" + speicalDeviceHeight +
        ", speicalDeviceMaxLength=" + speicalDeviceMaxLength +
        ", speicalDeviceMaxWidth=" + speicalDeviceMaxWidth +
        ", speicalDeviceMaxHeight=" + speicalDeviceMaxHeight +
        ", speicalDeviceAcc=" + speicalDeviceAcc +
        ", speicalDeviceWeight=" + speicalDeviceWeight +
        ", speicalDeviceShippingDate=" + speicalDeviceShippingDate +
        ", speicalDeviceWarranty=" + speicalDeviceWarranty +
        ", speicalDeviceStatusRuningIdleMaintance=" + speicalDeviceStatusRuningIdleMaintance +
        ", speicalDeviceUsingStartDate=" + speicalDeviceUsingStartDate +
        ", speicalDeviceMaintancePlanDate=" + speicalDeviceMaintancePlanDate +
        "}";
    }
}
