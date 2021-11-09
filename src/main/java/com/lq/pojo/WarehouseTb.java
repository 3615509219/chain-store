package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class WarehouseTb implements Serializable {


    private static final long serialVersionUID = 6245909124759037666L;
    @TableId(value = "warehouse_Id", type = IdType.AUTO)
    private Integer warehouseId;

    private String warehousePn;

    private Integer manufactureId;

    private String category;

    private String warehouseName;

    private String warehouseDescribe;

    private Integer warehouseNumber;

    private String manufacturer;

    private String storageTime;

    private String dateOfManufacture;

    private String colour;

    private String texture;

    private Double density;

    private Double accuracy;

    private Double particleSize;

    private String size;

    private String equipmentModel;

    private String imgAddr;

    private String remarks;


    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehousePn() {
        return warehousePn;
    }

    public void setWarehousePn(String warehousePn) {
        this.warehousePn = warehousePn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseDescribe() {
        return warehouseDescribe;
    }

    public void setWarehouseDescribe(String warehouseDescribe) {
        this.warehouseDescribe = warehouseDescribe;
    }

    public Integer getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(Integer warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(String storageTime) {
        this.storageTime = storageTime;
    }

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
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

    @Override
    public String toString() {
        return "WarehouseTb{" +
        "warehouseId=" + warehouseId +
        ", warehousePn=" + warehousePn +
        ", category=" + category +
        ", warehouseName=" + warehouseName +
        ", warehouseDescribe=" + warehouseDescribe +
        ", warehouseNumber=" + warehouseNumber +
        ", manufacturer=" + manufacturer +
        ", storageTime=" + storageTime +
        ", dateOfManufacture=" + dateOfManufacture +
        ", colour=" + colour +
        ", texture=" + texture +
        ", density=" + density +
        ", accuracy=" + accuracy +
        ", particleSize=" + particleSize +
        ", size=" + size +
        ", imgAddr=" + imgAddr +
        ", remarks=" + remarks +
        "}";
    }
}
