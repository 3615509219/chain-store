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
 * @since 2021-05-21
 */
public class CategoryTb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "category_Id", type = IdType.AUTO)
    private Integer categoryId;

    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;

    private String finishedGoodsCategory;


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPurchaseOrderProductPnId() {
        return purchaseOrderProductPnId;
    }

    public void setPurchaseOrderProductPnId(Integer purchaseOrderProductPnId) {
        this.purchaseOrderProductPnId = purchaseOrderProductPnId;
    }

    public String getFinishedGoodsCategory() {
        return finishedGoodsCategory;
    }

    public void setFinishedGoodsCategory(String finishedGoodsCategory) {
        this.finishedGoodsCategory = finishedGoodsCategory;
    }

    @Override
    public String toString() {
        return "CategoryTb{" +
        "categoryId=" + categoryId +
        ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        ", finishedGoodsCategory=" + finishedGoodsCategory +
        "}";
    }
}
