package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-05-25
 */
@Data
public class ProductImgTb implements Serializable {


    private static final long serialVersionUID = 1090909579231168861L;
    @TableField("customer_cart_ID")
    private Integer customerCartID;

    private String productImg;

    private String commentBz;


    @Override
    public String toString() {
        return "ProductImgTb{" +
        "productId=" + customerCartID +
        ", productImg=" + productImg +
        "}";
    }
}
