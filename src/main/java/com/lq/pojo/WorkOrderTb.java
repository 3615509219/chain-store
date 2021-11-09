package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
//工单表
public class WorkOrderTb implements Serializable {

    private static final long serialVersionUID = -2462512639347124685L;
    private String workOrder;

    @TableId(value = "work_order_ID", type = IdType.AUTO)
    private Integer workOrderId;

    private Integer workOrderProductTotalQty;

    private String workOrderContactWindow;

    private String workOrderContactWindowPhone;

    private String workOrderTechContactWindow;

    private String workOrderTechContactWindowPhone;

    private LocalDateTime workOrderOpenDate;

    private LocalDateTime workOrderCloseDate;

    private LocalDateTime workOrderEstimateEndDate;

    private LocalDateTime workOrderShippingMinsTargetDate;

    @TableField("manufacture_ID")
    private String manufactureId;

    @TableField("work_order_ID_total_price")
    private Double workOrderIdTotalPrice;

    @TableField("printer_ID_material_PN")
    private String printerIdMaterialPn;

    @TableField("black_agent_PN")
    private String blackAgentPn;

    @TableField("yellow_agent_PN")
    private String yellowAgentPn;

    @TableField("detailing_agent_PN")
    private String detailingAgentPn;

    @TableField("fusing_agent_PN")
    private String fusingAgentPn;

    @TableField("bright_fusing_agent_PN")
    private String brightFusingAgentPn;

    @TableField("magenta_agent_PN")
    private String magentaAgentPn;

    @TableField("cyan_agent_PN")
    private String cyanAgentPn;

    @TableField("package_box_PN")
    private String packageBoxPn;


    public String getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
    }

    public Integer getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Integer workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Integer getWorkOrderProductTotalQty() {
        return workOrderProductTotalQty;
    }

    public void setWorkOrderProductTotalQty(Integer workOrderProductTotalQty) {
        this.workOrderProductTotalQty = workOrderProductTotalQty;
    }

    public String getWorkOrderContactWindow() {
        return workOrderContactWindow;
    }

    public void setWorkOrderContactWindow(String workOrderContactWindow) {
        this.workOrderContactWindow = workOrderContactWindow;
    }

    public String getWorkOrderContactWindowPhone() {
        return workOrderContactWindowPhone;
    }

    public void setWorkOrderContactWindowPhone(String workOrderContactWindowPhone) {
        this.workOrderContactWindowPhone = workOrderContactWindowPhone;
    }

    public String getWorkOrderTechContactWindow() {
        return workOrderTechContactWindow;
    }

    public void setWorkOrderTechContactWindow(String workOrderTechContactWindow) {
        this.workOrderTechContactWindow = workOrderTechContactWindow;
    }

    public String getWorkOrderTechContactWindowPhone() {
        return workOrderTechContactWindowPhone;
    }

    public void setWorkOrderTechContactWindowPhone(String workOrderTechContactWindowPhone) {
        this.workOrderTechContactWindowPhone = workOrderTechContactWindowPhone;
    }

    public LocalDateTime getWorkOrderOpenDate() {
        return workOrderOpenDate;
    }

    public void setWorkOrderOpenDate(LocalDateTime workOrderOpenDate) {
        this.workOrderOpenDate = workOrderOpenDate;
    }

    public LocalDateTime getWorkOrderCloseDate() {
        return workOrderCloseDate;
    }

    public void setWorkOrderCloseDate(LocalDateTime workOrderCloseDate) {
        this.workOrderCloseDate = workOrderCloseDate;
    }

    public LocalDateTime getWorkOrderEstimateEndDate() {
        return workOrderEstimateEndDate;
    }

    public void setWorkOrderEstimateEndDate(LocalDateTime workOrderEstimateEndDate) {
        this.workOrderEstimateEndDate = workOrderEstimateEndDate;
    }

    public LocalDateTime getWorkOrderShippingMinsTargetDate() {
        return workOrderShippingMinsTargetDate;
    }

    public void setWorkOrderShippingMinsTargetDate(LocalDateTime workOrderShippingMinsTargetDate) {
        this.workOrderShippingMinsTargetDate = workOrderShippingMinsTargetDate;
    }

    public String getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(String manufactureId) {
        this.manufactureId = manufactureId;
    }

    public Double getWorkOrderIdTotalPrice() {
        return workOrderIdTotalPrice;
    }

    public void setWorkOrderIdTotalPrice(Double workOrderIdTotalPrice) {
        this.workOrderIdTotalPrice = workOrderIdTotalPrice;
    }

    public String getPrinterIdMaterialPn() {
        return printerIdMaterialPn;
    }

    public void setPrinterIdMaterialPn(String printerIdMaterialPn) {
        this.printerIdMaterialPn = printerIdMaterialPn;
    }

    public String getBlackAgentPn() {
        return blackAgentPn;
    }

    public void setBlackAgentPn(String blackAgentPn) {
        this.blackAgentPn = blackAgentPn;
    }

    public String getYellowAgentPn() {
        return yellowAgentPn;
    }

    public void setYellowAgentPn(String yellowAgentPn) {
        this.yellowAgentPn = yellowAgentPn;
    }

    public String getDetailingAgentPn() {
        return detailingAgentPn;
    }

    public void setDetailingAgentPn(String detailingAgentPn) {
        this.detailingAgentPn = detailingAgentPn;
    }

    public String getFusingAgentPn() {
        return fusingAgentPn;
    }

    public void setFusingAgentPn(String fusingAgentPn) {
        this.fusingAgentPn = fusingAgentPn;
    }

    public String getBrightFusingAgentPn() {
        return brightFusingAgentPn;
    }

    public void setBrightFusingAgentPn(String brightFusingAgentPn) {
        this.brightFusingAgentPn = brightFusingAgentPn;
    }

    public String getMagentaAgentPn() {
        return magentaAgentPn;
    }

    public void setMagentaAgentPn(String magentaAgentPn) {
        this.magentaAgentPn = magentaAgentPn;
    }

    public String getCyanAgentPn() {
        return cyanAgentPn;
    }

    public void setCyanAgentPn(String cyanAgentPn) {
        this.cyanAgentPn = cyanAgentPn;
    }

    public String getPackageBoxPn() {
        return packageBoxPn;
    }

    public void setPackageBoxPn(String packageBoxPn) {
        this.packageBoxPn = packageBoxPn;
    }

    @Override
    public String toString() {
        return "WorkOrderTb{" +
        "workOrder=" + workOrder +
        ", workOrderId=" + workOrderId +
        ", workOrderProductTotalQty=" + workOrderProductTotalQty +
        ", workOrderContactWindow=" + workOrderContactWindow +
        ", workOrderContactWindowPhone=" + workOrderContactWindowPhone +
        ", workOrderTechContactWindow=" + workOrderTechContactWindow +
        ", workOrderTechContactWindowPhone=" + workOrderTechContactWindowPhone +
        ", workOrderOpenDate=" + workOrderOpenDate +
        ", workOrderCloseDate=" + workOrderCloseDate +
        ", workOrderEstimateEndDate=" + workOrderEstimateEndDate +
        ", workOrderShippingMinsTargetDate=" + workOrderShippingMinsTargetDate +
        ", manufactureId=" + manufactureId +
        ", workOrderIdTotalPrice=" + workOrderIdTotalPrice +
        ", printerIdMaterialPn=" + printerIdMaterialPn +
        ", blackAgentPn=" + blackAgentPn +
        ", yellowAgentPn=" + yellowAgentPn +
        ", detailingAgentPn=" + detailingAgentPn +
        ", fusingAgentPn=" + fusingAgentPn +
        ", brightFusingAgentPn=" + brightFusingAgentPn +
        ", magentaAgentPn=" + magentaAgentPn +
        ", cyanAgentPn=" + cyanAgentPn +
        ", packageBoxPn=" + packageBoxPn +
        "}";
    }
}
