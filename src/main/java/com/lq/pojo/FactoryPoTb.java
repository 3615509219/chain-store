package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("factory_Po_tb")
public class FactoryPoTb implements Serializable {


    private static final long serialVersionUID = 8536053676654754863L;
    @TableId(value = "factory_Po_Id", type = IdType.AUTO)
    private Integer factoryPoId;

    @TableField("factory_Po")
    private String factoryPo;

    private String factoryName;

    private String factoryTime;

    private Integer factoryPrice;

    private String consignee;

    private String telephone;

    private String addressOfConsignee;

    private String payTime;

    private String orderStatus;

    private Integer manufactureId;

    private String orderType;

    private String logistic;


    public Integer getFactoryPoId() {
        return factoryPoId;
    }

    public void setFactoryPoId(Integer factoryPoId) {
        this.factoryPoId = factoryPoId;
    }

    public String getFactoryPo() {
        return factoryPo;
    }

    public void setFactoryPo(String factoryPo) {
        this.factoryPo = factoryPo;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryTime() {
        return factoryTime;
    }

    public void setFactoryTime(String factoryTime) {
        this.factoryTime = factoryTime;
    }

    public Integer getFactoryPrice() {
        return factoryPrice;
    }

    public void setFactoryPrice(Integer factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddressOfConsignee() {
        return addressOfConsignee;
    }

    public void setAddressOfConsignee(String addressOfConsignee) {
        this.addressOfConsignee = addressOfConsignee;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "FactoryPoTb{" +
        "factoryPoId=" + factoryPoId +
        ", factoryPo=" + factoryPo +
        ", factoryName=" + factoryName +
        ", factoryTime=" + factoryTime +
        ", factoryPrice=" + factoryPrice +
        ", consignee=" + consignee +
        ", telephone=" + telephone +
        ", addressOfConsignee=" + addressOfConsignee +
        ", payTime=" + payTime +
        ", orderStatus=" + orderStatus +
        "}";
    }
}
