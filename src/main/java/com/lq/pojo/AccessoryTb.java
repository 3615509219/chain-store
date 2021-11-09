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
//打印机配件表
public class AccessoryTb implements Serializable {


    private static final long serialVersionUID = -832227865332245051L;
    @TableId("accessory_PN")
    private String accessoryPn;

    private String accessoryName;

    private String accessoryDescription;

    private String accessorySupplier;

    private Double sellAccessoryUnitPrice;

    private Double purchaseAccessoryUnitPrice;


    public String getAccessoryPn() {
        return accessoryPn;
    }

    public void setAccessoryPn(String accessoryPn) {
        this.accessoryPn = accessoryPn;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public String getAccessoryDescription() {
        return accessoryDescription;
    }

    public void setAccessoryDescription(String accessoryDescription) {
        this.accessoryDescription = accessoryDescription;
    }

    public String getAccessorySupplier() {
        return accessorySupplier;
    }

    public void setAccessorySupplier(String accessorySupplier) {
        this.accessorySupplier = accessorySupplier;
    }

    public Double getSellAccessoryUnitPrice() {
        return sellAccessoryUnitPrice;
    }

    public void setSellAccessoryUnitPrice(Double sellAccessoryUnitPrice) {
        this.sellAccessoryUnitPrice = sellAccessoryUnitPrice;
    }

    public Double getPurchaseAccessoryUnitPrice() {
        return purchaseAccessoryUnitPrice;
    }

    public void setPurchaseAccessoryUnitPrice(Double purchaseAccessoryUnitPrice) {
        this.purchaseAccessoryUnitPrice = purchaseAccessoryUnitPrice;
    }

    @Override
    public String toString() {
        return "AccessoryTb{" +
        "accessoryPn=" + accessoryPn +
        ", accessoryName=" + accessoryName +
        ", accessoryDescription=" + accessoryDescription +
        ", accessorySupplier=" + accessorySupplier +
        ", sellAccessoryUnitPrice=" + sellAccessoryUnitPrice +
        ", purchaseAccessoryUnitPrice=" + purchaseAccessoryUnitPrice +
        "}";
    }
}
