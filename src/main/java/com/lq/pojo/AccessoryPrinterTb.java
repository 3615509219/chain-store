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
//打印机和配件的中间表
public class AccessoryPrinterTb implements Serializable {

    private static final long serialVersionUID = -8654280081473798742L;
    @TableField("accessory_PN")
    private String accessoryPn;

    @TableField("printer_ID")
    private Integer printerId;


    public String getAccessoryPn() {
        return accessoryPn;
    }

    public void setAccessoryPn(String accessoryPn) {
        this.accessoryPn = accessoryPn;
    }

    public Integer getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Integer printerId) {
        this.printerId = printerId;
    }

    @Override
    public String toString() {
        return "AccessoryPrinterTb{" +
        "accessoryPn=" + accessoryPn +
        ", printerId=" + printerId +
        "}";
    }
}
