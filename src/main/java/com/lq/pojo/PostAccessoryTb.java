package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
//后处理工站配件表
public class PostAccessoryTb implements Serializable {

    private static final long serialVersionUID = 1851304126058518359L;
    @TableId("post_device_accessory_PN")
    private String postDeviceAccessoryPn;

    private String postDeviceAccessoryName;

    private String postDeviceAccessoryDescription;

    private String postDeviceAccessorySupplier;

    private Double sellPostDeviceAccessoryUnitPrice;

    private Double purchasePostDeviceAccessoryUnitPrice;


    public String getPostDeviceAccessoryPn() {
        return postDeviceAccessoryPn;
    }

    public void setPostDeviceAccessoryPn(String postDeviceAccessoryPn) {
        this.postDeviceAccessoryPn = postDeviceAccessoryPn;
    }

    public String getPostDeviceAccessoryName() {
        return postDeviceAccessoryName;
    }

    public void setPostDeviceAccessoryName(String postDeviceAccessoryName) {
        this.postDeviceAccessoryName = postDeviceAccessoryName;
    }

    public String getPostDeviceAccessoryDescription() {
        return postDeviceAccessoryDescription;
    }

    public void setPostDeviceAccessoryDescription(String postDeviceAccessoryDescription) {
        this.postDeviceAccessoryDescription = postDeviceAccessoryDescription;
    }

    public String getPostDeviceAccessorySupplier() {
        return postDeviceAccessorySupplier;
    }

    public void setPostDeviceAccessorySupplier(String postDeviceAccessorySupplier) {
        this.postDeviceAccessorySupplier = postDeviceAccessorySupplier;
    }

    public Double getSellPostDeviceAccessoryUnitPrice() {
        return sellPostDeviceAccessoryUnitPrice;
    }

    public void setSellPostDeviceAccessoryUnitPrice(Double sellPostDeviceAccessoryUnitPrice) {
        this.sellPostDeviceAccessoryUnitPrice = sellPostDeviceAccessoryUnitPrice;
    }

    public Double getPurchasePostDeviceAccessoryUnitPrice() {
        return purchasePostDeviceAccessoryUnitPrice;
    }

    public void setPurchasePostDeviceAccessoryUnitPrice(Double purchasePostDeviceAccessoryUnitPrice) {
        this.purchasePostDeviceAccessoryUnitPrice = purchasePostDeviceAccessoryUnitPrice;
    }

    @Override
    public String toString() {
        return "PostAccessoryTb{" +
        "postDeviceAccessoryPn=" + postDeviceAccessoryPn +
        ", postDeviceAccessoryName=" + postDeviceAccessoryName +
        ", postDeviceAccessoryDescription=" + postDeviceAccessoryDescription +
        ", postDeviceAccessorySupplier=" + postDeviceAccessorySupplier +
        ", sellPostDeviceAccessoryUnitPrice=" + sellPostDeviceAccessoryUnitPrice +
        ", purchasePostDeviceAccessoryUnitPrice=" + purchasePostDeviceAccessoryUnitPrice +
        "}";
    }
}
