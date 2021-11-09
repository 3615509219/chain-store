package com.lq.pojo;

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
//打印机和原材料的中间表
public class PrinterFactoryMaterialInventoryTb implements Serializable {

    private static final long serialVersionUID = -830053739963472826L;
    @TableField("printer_ID")
    private Integer printerId;

    @TableField("factory_material_PN")
    private String factoryMaterialPn;


    public Integer getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Integer printerId) {
        this.printerId = printerId;
    }

    public String getFactoryMaterialPn() {
        return factoryMaterialPn;
    }

    public void setFactoryMaterialPn(String factoryMaterialPn) {
        this.factoryMaterialPn = factoryMaterialPn;
    }

    @Override
    public String toString() {
        return "PrinterFactoryMaterialInventoryTb{" +
        "printerId=" + printerId +
        ", factoryMaterialPn=" + factoryMaterialPn +
        "}";
    }
}
