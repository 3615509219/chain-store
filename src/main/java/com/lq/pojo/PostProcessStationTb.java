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
//后处理工站表
public class PostProcessStationTb implements Serializable {

    private static final long serialVersionUID = -7070752317399960896L;
    @TableId(value = "post_device_ID", type = IdType.AUTO)
    private Integer postDeviceId;

    private String postDevice;

    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;

    private String purchaseOrderProductPn;

    private LocalDateTime postProcessBeginingDateTime;

    private LocalDateTime postProcessEstimateEndDateTime;

    private LocalDateTime postProcessActualEndDateTime;


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

    public LocalDateTime getPostProcessBeginingDateTime() {
        return postProcessBeginingDateTime;
    }

    public void setPostProcessBeginingDateTime(LocalDateTime postProcessBeginingDateTime) {
        this.postProcessBeginingDateTime = postProcessBeginingDateTime;
    }

    public LocalDateTime getPostProcessEstimateEndDateTime() {
        return postProcessEstimateEndDateTime;
    }

    public void setPostProcessEstimateEndDateTime(LocalDateTime postProcessEstimateEndDateTime) {
        this.postProcessEstimateEndDateTime = postProcessEstimateEndDateTime;
    }

    public LocalDateTime getPostProcessActualEndDateTime() {
        return postProcessActualEndDateTime;
    }

    public void setPostProcessActualEndDateTime(LocalDateTime postProcessActualEndDateTime) {
        this.postProcessActualEndDateTime = postProcessActualEndDateTime;
    }

    @Override
    public String toString() {
        return "PostProcessStationTb{" +
        "postDeviceId=" + postDeviceId +
        ", postDevice=" + postDevice +
        ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        ", purchaseOrderProductPn=" + purchaseOrderProductPn +
        ", postProcessBeginingDateTime=" + postProcessBeginingDateTime +
        ", postProcessEstimateEndDateTime=" + postProcessEstimateEndDateTime +
        ", postProcessActualEndDateTime=" + postProcessActualEndDateTime +
        "}";
    }
}
