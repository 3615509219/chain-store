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
public class CartImgTb implements Serializable {


    private static final long serialVersionUID = -5921769425646156896L;
    @TableField("customer_cart_ID")
    private Integer customerCartId;

    private String cartImg;

    private String commentBz;


    public Integer getCustomerCartId() {
        return customerCartId;
    }

    public void setCustomerCartId(Integer customerCartId) {
        this.customerCartId = customerCartId;
    }

    public String getCartImg() {
        return cartImg;
    }

    public void setCartImg(String cartImg) {
        this.cartImg = cartImg;
    }

    @Override
    public String toString() {
        return "CartImgTb{" +
        "customerCartId=" + customerCartId +
        ", cartImg=" + cartImg +
        "}";
    }
}
