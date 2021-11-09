package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-05-31
 */
public class BuyNowImgTb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("product_Id")
    private Integer productId;

    private String productImg;

    private String productBz;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductBz() {
        return productBz;
    }

    public void setProductBz(String productBz) {
        this.productBz = productBz;
    }

    @Override
    public String toString() {
        return "BuyNowImgTb{" +
        "productId=" + productId +
        ", productImg=" + productImg +
        ", productBz=" + productBz +
        "}";
    }
}
