package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-04-15
 */
public class ManufactureShopTb implements Serializable {


    private static final long serialVersionUID = 4914733391218527843L;
    @TableId(value = "shop_Id", type = IdType.AUTO)
    private Integer shopId;

    private String shopPn;

    private String category;

    private String shopName;

    private String shopDescribe;

    private String manufacturer;

    private String colour;

    private String texture;

    private Double density;

    private Double accuracy;

    private Double particleSize;

    private String size;

    private String equipmentModel;

    private String imgAddr;

    private String remarks;

    private Double purchasePrice;

    private Double sellingPrice;


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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDescribe() {
        return shopDescribe;
    }

    public void setShopDescribe(String shopDescribe) {
        this.shopDescribe = shopDescribe;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Double getParticleSize() {
        return particleSize;
    }

    public void setParticleSize(Double particleSize) {
        this.particleSize = particleSize;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
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

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        return "ManufactureShopTb{" +
        "shopId=" + shopId +
        ", shopPn=" + shopPn +
        ", category=" + category +
        ", shopName=" + shopName +
        ", shopDescribe=" + shopDescribe +
        ", manufacturer=" + manufacturer +
        ", colour=" + colour +
        ", texture=" + texture +
        ", density=" + density +
        ", accuracy=" + accuracy +
        ", particleSize=" + particleSize +
        ", size=" + size +
        ", equipmentModel=" + equipmentModel +
        ", imgAddr=" + imgAddr +
        ", remarks=" + remarks +
        ", purchasePrice=" + purchasePrice +
        ", sellingPrice=" + sellingPrice +
        "}";
    }
}
