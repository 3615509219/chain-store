package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
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
//打印机设备表
@TableName("printer_ID_status_tb")
public class PrinterIdStatusTb implements Serializable {

    private static final long serialVersionUID = 3094175274789635411L;
    @TableId(value = "printer_ID", type = IdType.AUTO)
    private Integer printerId;

    private String printer;

    @TableField("printer_status_Runing_idle_maintance")
    private String printerStatusRuningIdleMaintance;

    private LocalDateTime printerUsingStartDate;

    private LocalDateTime printerMaintancePlanDate;

    private Double restMaterialUnits;

    private Integer restBlackAgentUnits;

    private Integer restYellowAgentUnits;

    private Integer restDetailingAgentUnits;

    private Integer restFusingAgentUnits;

    private Integer restBrightFusingAgentUnits;

    private Integer restMagentaAgentUnits;

    private Integer restCyanAgentUnits;

    @TableField("factory_material_PN")
    private String factoryMaterialPn;


    public Integer getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Integer printerId) {
        this.printerId = printerId;
    }

    public String getPrinter() {
        return printer;
    }

    public void setPrinter(String printer) {
        this.printer = printer;
    }

    public String getPrinterStatusRuningIdleMaintance() {
        return printerStatusRuningIdleMaintance;
    }

    public void setPrinterStatusRuningIdleMaintance(String printerStatusRuningIdleMaintance) {
        this.printerStatusRuningIdleMaintance = printerStatusRuningIdleMaintance;
    }

    public LocalDateTime getPrinterUsingStartDate() {
        return printerUsingStartDate;
    }

    public void setPrinterUsingStartDate(LocalDateTime printerUsingStartDate) {
        this.printerUsingStartDate = printerUsingStartDate;
    }

    public LocalDateTime getPrinterMaintancePlanDate() {
        return printerMaintancePlanDate;
    }

    public void setPrinterMaintancePlanDate(LocalDateTime printerMaintancePlanDate) {
        this.printerMaintancePlanDate = printerMaintancePlanDate;
    }

    public Double getRestMaterialUnits() {
        return restMaterialUnits;
    }

    public void setRestMaterialUnits(Double restMaterialUnits) {
        this.restMaterialUnits = restMaterialUnits;
    }

    public Integer getRestBlackAgentUnits() {
        return restBlackAgentUnits;
    }

    public void setRestBlackAgentUnits(Integer restBlackAgentUnits) {
        this.restBlackAgentUnits = restBlackAgentUnits;
    }

    public Integer getRestYellowAgentUnits() {
        return restYellowAgentUnits;
    }

    public void setRestYellowAgentUnits(Integer restYellowAgentUnits) {
        this.restYellowAgentUnits = restYellowAgentUnits;
    }

    public Integer getRestDetailingAgentUnits() {
        return restDetailingAgentUnits;
    }

    public void setRestDetailingAgentUnits(Integer restDetailingAgentUnits) {
        this.restDetailingAgentUnits = restDetailingAgentUnits;
    }

    public Integer getRestFusingAgentUnits() {
        return restFusingAgentUnits;
    }

    public void setRestFusingAgentUnits(Integer restFusingAgentUnits) {
        this.restFusingAgentUnits = restFusingAgentUnits;
    }

    public Integer getRestBrightFusingAgentUnits() {
        return restBrightFusingAgentUnits;
    }

    public void setRestBrightFusingAgentUnits(Integer restBrightFusingAgentUnits) {
        this.restBrightFusingAgentUnits = restBrightFusingAgentUnits;
    }

    public Integer getRestMagentaAgentUnits() {
        return restMagentaAgentUnits;
    }

    public void setRestMagentaAgentUnits(Integer restMagentaAgentUnits) {
        this.restMagentaAgentUnits = restMagentaAgentUnits;
    }

    public Integer getRestCyanAgentUnits() {
        return restCyanAgentUnits;
    }

    public void setRestCyanAgentUnits(Integer restCyanAgentUnits) {
        this.restCyanAgentUnits = restCyanAgentUnits;
    }

    public String getFactoryMaterialPn() {
        return factoryMaterialPn;
    }

    public void setFactoryMaterialPn(String factoryMaterialPn) {
        this.factoryMaterialPn = factoryMaterialPn;
    }

    @Override
    public String toString() {
        return "PrinterIdStatusTb{" +
        "printerId=" + printerId +
        ", printer=" + printer +
        ", printerStatusRuningIdleMaintance=" + printerStatusRuningIdleMaintance +
        ", printerUsingStartDate=" + printerUsingStartDate +
        ", printerMaintancePlanDate=" + printerMaintancePlanDate +
        ", restMaterialUnits=" + restMaterialUnits +
        ", restBlackAgentUnits=" + restBlackAgentUnits +
        ", restYellowAgentUnits=" + restYellowAgentUnits +
        ", restDetailingAgentUnits=" + restDetailingAgentUnits +
        ", restFusingAgentUnits=" + restFusingAgentUnits +
        ", restBrightFusingAgentUnits=" + restBrightFusingAgentUnits +
        ", restMagentaAgentUnits=" + restMagentaAgentUnits +
        ", restCyanAgentUnits=" + restCyanAgentUnits +
        ", factoryMaterialPn=" + factoryMaterialPn +
        "}";
    }
}
