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
 * @since 2021-01-13
 */
//物品流程表
public class RoductManufactureFlowTb implements Serializable {

    private static final long serialVersionUID = -69167049057012378L;
    @TableId(value = "product_manufacture_flow_ID", type = IdType.AUTO)
    private Integer productManufactureFlowId;

    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;

    private String purchaseOrderProductPn;

    private String printingStation;

    @TableField("printing_station_ID")
    private Integer printingStationId;

    private String printingStationStatus;

    private String postProcessStation;

    @TableField("post_process_station_ID")
    private Integer postProcessStationId;

    private String postProcessStationStatus;

    @TableField("special_process_Station")
    private String specialProcessStation;

    @TableField("special_process_station_ID")
    private Integer specialProcessStationId;

    private String specialProcessStationStatus;

    private String packingStation;

    @TableField("packing_station_ID")
    private Integer packingStationId;

    private String packingStationStatus;

    private String logisticsStation;

    @TableField("logistics_station_ID")
    private Integer logisticsStationId;

    private String logisticsStationStatus;


    public Integer getProductManufactureFlowId() {
        return productManufactureFlowId;
    }

    public void setProductManufactureFlowId(Integer productManufactureFlowId) {
        this.productManufactureFlowId = productManufactureFlowId;
    }

    public Integer getPurchaseOrderProductPnId() {
        return purchaseOrderProductPnId;
    }

    public void setPurchaseOrderProductPnId(Integer purchaseOrderProductPnId) {
        this.purchaseOrderProductPnId = purchaseOrderProductPnId;
    }

    public String getPurchaseOrderProductPn() {
        return purchaseOrderProductPn;
    }

    public void setPurchaseOrderProductPn(String purchaseOrderProductPn) {
        this.purchaseOrderProductPn = purchaseOrderProductPn;
    }

    public String getPrintingStation() {
        return printingStation;
    }

    public void setPrintingStation(String printingStation) {
        this.printingStation = printingStation;
    }

    public Integer getPrintingStationId() {
        return printingStationId;
    }

    public void setPrintingStationId(Integer printingStationId) {
        this.printingStationId = printingStationId;
    }

    public String getPrintingStationStatus() {
        return printingStationStatus;
    }

    public void setPrintingStationStatus(String printingStationStatus) {
        this.printingStationStatus = printingStationStatus;
    }

    public String getPostProcessStation() {
        return postProcessStation;
    }

    public void setPostProcessStation(String postProcessStation) {
        this.postProcessStation = postProcessStation;
    }

    public Integer getPostProcessStationId() {
        return postProcessStationId;
    }

    public void setPostProcessStationId(Integer postProcessStationId) {
        this.postProcessStationId = postProcessStationId;
    }

    public String getPostProcessStationStatus() {
        return postProcessStationStatus;
    }

    public void setPostProcessStationStatus(String postProcessStationStatus) {
        this.postProcessStationStatus = postProcessStationStatus;
    }

    public String getSpecialProcessStation() {
        return specialProcessStation;
    }

    public void setSpecialProcessStation(String specialProcessStation) {
        this.specialProcessStation = specialProcessStation;
    }

    public Integer getSpecialProcessStationId() {
        return specialProcessStationId;
    }

    public void setSpecialProcessStationId(Integer specialProcessStationId) {
        this.specialProcessStationId = specialProcessStationId;
    }

    public String getSpecialProcessStationStatus() {
        return specialProcessStationStatus;
    }

    public void setSpecialProcessStationStatus(String specialProcessStationStatus) {
        this.specialProcessStationStatus = specialProcessStationStatus;
    }

    public String getPackingStation() {
        return packingStation;
    }

    public void setPackingStation(String packingStation) {
        this.packingStation = packingStation;
    }

    public Integer getPackingStationId() {
        return packingStationId;
    }

    public void setPackingStationId(Integer packingStationId) {
        this.packingStationId = packingStationId;
    }

    public String getPackingStationStatus() {
        return packingStationStatus;
    }

    public void setPackingStationStatus(String packingStationStatus) {
        this.packingStationStatus = packingStationStatus;
    }

    public String getLogisticsStation() {
        return logisticsStation;
    }

    public void setLogisticsStation(String logisticsStation) {
        this.logisticsStation = logisticsStation;
    }

    public Integer getLogisticsStationId() {
        return logisticsStationId;
    }

    public void setLogisticsStationId(Integer logisticsStationId) {
        this.logisticsStationId = logisticsStationId;
    }

    public String getLogisticsStationStatus() {
        return logisticsStationStatus;
    }

    public void setLogisticsStationStatus(String logisticsStationStatus) {
        this.logisticsStationStatus = logisticsStationStatus;
    }

    @Override
    public String toString() {
        return "RoductManufactureFlowTb{" +
        "productManufactureFlowId=" + productManufactureFlowId +
        ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        ", purchaseOrderProductPn=" + purchaseOrderProductPn +
        ", printingStation=" + printingStation +
        ", printingStationId=" + printingStationId +
        ", printingStationStatus=" + printingStationStatus +
        ", postProcessStation=" + postProcessStation +
        ", postProcessStationId=" + postProcessStationId +
        ", postProcessStationStatus=" + postProcessStationStatus +
        ", specialProcessStation=" + specialProcessStation +
        ", specialProcessStationId=" + specialProcessStationId +
        ", specialProcessStationStatus=" + specialProcessStationStatus +
        ", packingStation=" + packingStation +
        ", packingStationId=" + packingStationId +
        ", packingStationStatus=" + packingStationStatus +
        ", logisticsStation=" + logisticsStation +
        ", logisticsStationId=" + logisticsStationId +
        ", logisticsStationStatus=" + logisticsStationStatus +
        "}";
    }
}
