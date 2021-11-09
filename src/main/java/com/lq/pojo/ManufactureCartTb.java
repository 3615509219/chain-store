package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-04-15
 */
@Data
public class ManufactureCartTb implements Serializable {


    private static final long serialVersionUID = -5823956348471127762L;
    @TableId(value = "manufacture_cart_Id", type = IdType.AUTO)
    private Integer manufactureCartId;

    @TableField("shop_Id")
    private Integer shopId;

    private String shopPn;

    @TableField("manufacture_ID")
    private Integer manufactureId;

    private String shopName;

    private String category;

    private String shopDescribe;

    private String imgAddr;

    private String remarks;

    private Double sellingPrice;

    private Integer number;

    private String time;

    private Integer status;


    public Integer getManufactureCartId() {
        return manufactureCartId;
    }

    public void setManufactureCartId(Integer manufactureCartId) {
        this.manufactureCartId = manufactureCartId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopPn() {
        return shopPn;
    }

    public void setShopPn(String shopPn) {
        this.shopPn = shopPn;
    }

    public Integer getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(Integer manufactureId) {
        this.manufactureId = manufactureId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShopDescribe() {
        return shopDescribe;
    }

    public void setShopDescribe(String shopDescribe) {
        this.shopDescribe = shopDescribe;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ManufactureCartTb{" +
        "manufactureCartId=" + manufactureCartId +
        ", shopId=" + shopId +
        ", shopPn=" + shopPn +
        ", manufactureId=" + manufactureId +
        ", shopName=" + shopName +
        ", category=" + category +
        ", shopDescribe=" + shopDescribe +
        ", imgAddr=" + imgAddr +
        ", remarks=" + remarks +
        ", sellingPrice=" + sellingPrice +
        ", number=" + number +
        "}";
    }
}
