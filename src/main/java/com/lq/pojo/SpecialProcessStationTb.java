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
//特殊处理工站
public class SpecialProcessStationTb implements Serializable {

    private static final long serialVersionUID = -8785594830197170589L;
    @TableId(value = "speical_device_ID", type = IdType.AUTO)
    private Integer speicalDeviceId;

    private String speicalDevice;

    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;

    private String purchaseOrderProductPn;

    private LocalDateTime specialProcessBeginingDateTime;

    private LocalDateTime specialProcessEstimateEndDateTime;

    private LocalDateTime specialProcessActualEndDateTime;


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

    public Integer getPurchaseOrderProductPnId() {
        return purchaseOrderProductPnId;
    }

    public void setPurchaseOrderProductPnId(Integer purchaseOrderProductPnId) {
        this.purchaseOrderProductPnId = purchaseOrderProductPnId;
    }

    public String getPurchaseOrderProductPn() {
        return purchaseOrderProductPn;
    }

    public void setPurchaseOrderProductPn(String purchaseOrderProductPn) {
        this.purchaseOrderProductPn = purchaseOrderProductPn;
    }

    public LocalDateTime getSpecialProcessBeginingDateTime() {
        return specialProcessBeginingDateTime;
    }

    public void setSpecialProcessBeginingDateTime(LocalDateTime specialProcessBeginingDateTime) {
        this.specialProcessBeginingDateTime = specialProcessBeginingDateTime;
    }

    public LocalDateTime getSpecialProcessEstimateEndDateTime() {
        return specialProcessEstimateEndDateTime;
    }

    public void setSpecialProcessEstimateEndDateTime(LocalDateTime specialProcessEstimateEndDateTime) {
        this.specialProcessEstimateEndDateTime = specialProcessEstimateEndDateTime;
    }

    public LocalDateTime getSpecialProcessActualEndDateTime() {
        return specialProcessActualEndDateTime;
    }

    public void setSpecialProcessActualEndDateTime(LocalDateTime specialProcessActualEndDateTime) {
        this.specialProcessActualEndDateTime = specialProcessActualEndDateTime;
    }

    @Override
    public String toString() {
        return "SpecialProcessStationTb{" +
        "speicalDeviceId=" + speicalDeviceId +
        ", speicalDevice=" + speicalDevice +
        ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        ", purchaseOrderProductPn=" + purchaseOrderProductPn +
        ", specialProcessBeginingDateTime=" + specialProcessBeginingDateTime +
        ", specialProcessEstimateEndDateTime=" + specialProcessEstimateEndDateTime +
        ", specialProcessActualEndDateTime=" + specialProcessActualEndDateTime +
        "}";
    }
}
