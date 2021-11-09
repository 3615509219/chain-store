package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
//客户购物车表

@Data
public class CustomerCartTb implements Serializable {

    private static final long serialVersionUID = -8206055558041101527L;
    @TableId(value = "customer_cart_ID", type = IdType.AUTO)
    private Integer customerCartId;

    @TableField("account_ID")
    private Integer accountId;

    private String finishedGoodsName;

    private String purchaseOrderProductPn;
    private Integer finishedGoodsListSubId;
    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;

    private String finishedGoodsDescription;

    private Integer finishedGoodsQty;

    private String finishedGoodsCartDate;

    private Integer finishedGoodsStatus;

    private double unitPrice;

    private String imgAddr;

    private String comment;

    private Integer selectStatus;

    private Integer integral;

    private Integer jf;

    private Integer bz;

    private List<CartImgTb> cartImgTb;

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public Integer getSelectStatus() {
        return selectStatus;
    }

    public void setSelectStatus(Integer selectStatus) {
        this.selectStatus = selectStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getCustomerCartId() {
        return customerCartId;
    }

    public void setCustomerCartId(Integer customerCartId) {
        this.customerCartId = customerCartId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getFinishedGoodsName() {
        return finishedGoodsName;
    }

    public void setFinishedGoodsName(String finishedGoodsName) {
        this.finishedGoodsName = finishedGoodsName;
    }

    public String getPurchaseOrderProductPn() {
        return purchaseOrderProductPn;
    }

    public void setPurchaseOrderProductPn(String purchaseOrderProductPn) {
        this.purchaseOrderProductPn = purchaseOrderProductPn;
    }

    public Integer getPurchaseOrderProductPnId() {
        return purchaseOrderProductPnId;
    }

    public void setPurchaseOrderProductPnId(Integer purchaseOrderProductPnId) {
        this.purchaseOrderProductPnId = purchaseOrderProductPnId;
    }

    public String getFinishedGoodsDescription() {
        return finishedGoodsDescription;
    }

    public void setFinishedGoodsDescription(String finishedGoodsDescription) {
        this.finishedGoodsDescription = finishedGoodsDescription;
    }

    public Integer getFinishedGoodsQty() {
        return finishedGoodsQty;
    }

    public void setFinishedGoodsQty(Integer finishedGoodsQty) {
        this.finishedGoodsQty = finishedGoodsQty;
    }

    public String getFinishedGoodsCartDate() {
        return finishedGoodsCartDate;
    }

    public void setFinishedGoodsCartDate(String finishedGoodsCartDate) {
        this.finishedGoodsCartDate = finishedGoodsCartDate;
    }

    public Integer getFinishedGoodsStatus() {
        return finishedGoodsStatus;
    }

    public void setFinishedGoodsStatus(Integer finishedGoodsStatus) {
        this.finishedGoodsStatus = finishedGoodsStatus;
    }

    @Override
    public String toString() {
        return "CustomerCartTb{" +
                "customerCartId=" + customerCartId +
                ", accountId=" + accountId +
                ", finishedGoodsName='" + finishedGoodsName + '\'' +
                ", purchaseOrderProductPn='" + purchaseOrderProductPn + '\'' +
                ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
                ", finishedGoodsDescription='" + finishedGoodsDescription + '\'' +
                ", finishedGoodsQty=" + finishedGoodsQty +
                ", finishedGoodsCartDate='" + finishedGoodsCartDate + '\'' +
                ", finishedGoodsStatus=" + finishedGoodsStatus +
                ", unitPrice=" + unitPrice +
                ", comment='" + comment + '\'' +
                ", selectStatus=" + selectStatus +
                '}';
    }
}
