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
//打印机表
public class PrinterTb implements Serializable {

    private static final long serialVersionUID = -7826330971286674000L;
    @TableId(value = "printer_ID", type = IdType.AUTO)
    private Integer printerId;

    private String printer;

    private String printerVendor;

    @TableField("printer_IP_addr")
    private String printerIpAddr;

    private String printerModel;

    private Double printerLength;

    private Double printerWidth;

    private Double printerHeight;

    @TableField("printer_Max_length")
    private Double printerMaxLength;

    @TableField("printer_Max_width")
    private Double printerMaxWidth;

    @TableField("printer_Max_height")
    private Double printerMaxHeight;

    @TableField("printer_Color")
    private String printerColor;

    private Double printerAcc;

    private LocalDateTime printerShippingDate;

    private Integer printerWarranty;

    private Double printerWeight;


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

    public String getPrinterVendor() {
        return printerVendor;
    }

    public void setPrinterVendor(String printerVendor) {
        this.printerVendor = printerVendor;
    }

    public String getPrinterIpAddr() {
        return printerIpAddr;
    }

    public void setPrinterIpAddr(String printerIpAddr) {
        this.printerIpAddr = printerIpAddr;
    }

    public String getPrinterModel() {
        return printerModel;
    }

    public void setPrinterModel(String printerModel) {
        this.printerModel = printerModel;
    }

    public Double getPrinterLength() {
        return printerLength;
    }

    public void setPrinterLength(Double printerLength) {
        this.printerLength = printerLength;
    }

    public Double getPrinterWidth() {
        return printerWidth;
    }

    public void setPrinterWidth(Double printerWidth) {
        this.printerWidth = printerWidth;
    }

    public Double getPrinterHeight() {
        return printerHeight;
    }

    public void setPrinterHeight(Double printerHeight) {
        this.printerHeight = printerHeight;
    }

    public Double getPrinterMaxLength() {
        return printerMaxLength;
    }

    public void setPrinterMaxLength(Double printerMaxLength) {
        this.printerMaxLength = printerMaxLength;
    }

    public Double getPrinterMaxWidth() {
        return printerMaxWidth;
    }

    public void setPrinterMaxWidth(Double printerMaxWidth) {
        this.printerMaxWidth = printerMaxWidth;
    }

    public Double getPrinterMaxHeight() {
        return printerMaxHeight;
    }

    public void setPrinterMaxHeight(Double printerMaxHeight) {
        this.printerMaxHeight = printerMaxHeight;
    }

    public String getPrinterColor() {
        return printerColor;
    }

    public void setPrinterColor(String printerColor) {
        this.printerColor = printerColor;
    }

    public Double getPrinterAcc() {
        return printerAcc;
    }

    public void setPrinterAcc(Double printerAcc) {
        this.printerAcc = printerAcc;
    }

    public LocalDateTime getPrinterShippingDate() {
        return printerShippingDate;
    }

    public void setPrinterShippingDate(LocalDateTime printerShippingDate) {
        this.printerShippingDate = printerShippingDate;
    }

    public Integer getPrinterWarranty() {
        return printerWarranty;
    }

    public void setPrinterWarranty(Integer printerWarranty) {
        this.printerWarranty = printerWarranty;
    }

    public Double getPrinterWeight() {
        return printerWeight;
    }

    public void setPrinterWeight(Double printerWeight) {
        this.printerWeight = printerWeight;
    }

    @Override
    public String toString() {
        return "PrinterTb{" +
        "printerId=" + printerId +
        ", printer=" + printer +
        ", printerVendor=" + printerVendor +
        ", printerIpAddr=" + printerIpAddr +
        ", printerModel=" + printerModel +
        ", printerLength=" + printerLength +
        ", printerWidth=" + printerWidth +
        ", printerHeight=" + printerHeight +
        ", printerMaxLength=" + printerMaxLength +
        ", printerMaxWidth=" + printerMaxWidth +
        ", printerMaxHeight=" + printerMaxHeight +
        ", printerColor=" + printerColor +
        ", printerAcc=" + printerAcc +
        ", printerShippingDate=" + printerShippingDate +
        ", printerWarranty=" + printerWarranty +
        ", printerWeight=" + printerWeight +
        "}";
    }
}
