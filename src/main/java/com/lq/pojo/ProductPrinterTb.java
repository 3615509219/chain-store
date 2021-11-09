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
//打印机与物品的中间表
public class ProductPrinterTb implements Serializable {

    private static final long serialVersionUID = -3586642957689641843L;
    @TableField("printer_ID")
    private Integer printerId;

    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;


    public Integer getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Integer printerId) {
        this.printerId = printerId;
    }

    public Integer getPurchaseOrderProductPnId() {
        return purchaseOrderProductPnId;
    }

    public void setPurchaseOrderProductPnId(Integer purchaseOrderProductPnId) {
        this.purchaseOrderProductPnId = purchaseOrderProductPnId;
    }

    @Override
    public String toString() {
        return "ProductPrinterTb{" +
        "printerId=" + printerId +
        ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        "}";
    }
}
