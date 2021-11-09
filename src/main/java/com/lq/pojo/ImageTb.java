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
 * @since 2021-01-21
 */
//图片表
public class ImageTb implements Serializable {


    private static final long serialVersionUID = -7877856385901587523L;
    @TableId(value = "image_Id", type = IdType.AUTO)
    private Integer imageId;

    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;

    private String drawingImageAddr;

    private String shopImage;

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getPurchaseOrderProductPnId() {
        return purchaseOrderProductPnId;
    }

    public void setPurchaseOrderProductPnId(Integer purchaseOrderProductPnId) {
        this.purchaseOrderProductPnId = purchaseOrderProductPnId;
    }

    public String getDrawingImageAddr() {
        return drawingImageAddr;
    }

    public void setDrawingImageAddr(String drawingImageAddr) {
        this.drawingImageAddr = drawingImageAddr;
    }

    @Override
    public String toString() {
        return "ImageTb{" +
        "imageId=" + imageId +
        ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        ", drawingImageAddr=" + drawingImageAddr +
        "}";
    }
}
