package com.lq.pojo;

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
//订单和工单的中间表
public class PurchaseOrderWorkOrderTb implements Serializable {

    private static final long serialVersionUID = -4037495908837886030L;
    @TableField("purchase_order_number_ID")
    private Integer purchaseOrderNumberId;

    @TableField("work_order_ID")
    private Integer workOrderId;


    public Integer getPurchaseOrderNumberId() {
        return purchaseOrderNumberId;
    }

    public void setPurchaseOrderNumberId(Integer purchaseOrderNumberId) {
        this.purchaseOrderNumberId = purchaseOrderNumberId;
    }

    public Integer getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Integer workOrderId) {
        this.workOrderId = workOrderId;
    }

    @Override
    public String toString() {
        return "PurchaseOrderWorkOrderTb{" +
        "purchaseOrderNumberId=" + purchaseOrderNumberId +
        ", workOrderId=" + workOrderId +
        "}";
    }
}
