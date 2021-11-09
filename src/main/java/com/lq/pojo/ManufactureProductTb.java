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
 * @since 2021-04-15
 */
public class ManufactureProductTb implements Serializable {


    private static final long serialVersionUID = 5199395201500102742L;
    @TableId(value = "product_Id", type = IdType.AUTO)
    private Integer productId;

    private String productPn;

    private String category;

    private String productName;

    private String productDescribe;

    private String manufacturer;

    private String colour;

    private String texture;

    private Double density;

    private Double accuracy;

    private Double particleSize;

    private String size;

    private String imgAddr;

    private String remarks;

    private Double sellingPrice;

    @TableField("factory_Po")
    private String factoryPo;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductPn() {
        return productPn;
    }

    public void setProductPn(String productPn) {
        this.productPn = productPn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescribe() {
        return productDescribe;
    }

    public void setProductDescribe(String productDescribe) {
        this.productDescribe = productDescribe;
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

    public String getFactoryPo() {
        return factoryPo;
    }

    public void setFactoryPo(String factoryPo) {
        this.factoryPo = factoryPo;
    }

    @Override
    public String toString() {
        return "ManufactureProductTb{" +
        "productId=" + productId +
        ", productPn=" + productPn +
        ", category=" + category +
        ", productName=" + productName +
        ", productDescribe=" + productDescribe +
        ", manufacturer=" + manufacturer +
        ", colour=" + colour +
        ", texture=" + texture +
        ", density=" + density +
        ", accuracy=" + accuracy +
        ", particleSize=" + particleSize +
        ", size=" + size +
        ", imgAddr=" + imgAddr +
        ", remarks=" + remarks +
        ", sellingPrice=" + sellingPrice +
        ", factoryPo=" + factoryPo +
        "}";
    }
}
