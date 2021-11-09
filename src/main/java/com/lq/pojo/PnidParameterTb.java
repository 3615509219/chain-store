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
//打印机实际参数表
@TableName("PNID_parameter_tb")
public class PnidParameterTb implements Serializable {

    private static final long serialVersionUID = 3403436118087758506L;
    @TableId(value = "PNID_parameter_ID", type = IdType.AUTO)
    private Integer pnidParameterId;

    private String purchaseOrderProductName;

    private Double productLength;

    private Double productWidth;

    private Double productHeight;

    private String purchaseOrderProductPn;

    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;

    @TableField("product_pn_ID_begining_datetime")
    private LocalDateTime productPnIdBeginingDatetime;

    @TableField("product_pn_ID_end_datetime")
    private LocalDateTime productPnIdEndDatetime;

    @TableField("product_pn_ID_completed_percent_datetime")
    private Integer productPnIdCompletedPercentDatetime;

    @TableField("product_pn_ID_material_using_units")
    private Double productPnIdMaterialUsingUnits;

    @TableField("product_pn_ID_black_agent_using_units")
    private Integer productPnIdBlackAgentUsingUnits;

    @TableField("product_pn_ID_yellow_agent_using_units")
    private Integer productPnIdYellowAgentUsingUnits;

    @TableField("product_pn_ID_detailing_agent_using_units")
    private Integer productPnIdDetailingAgentUsingUnits;

    @TableField("product_pn_ID_fusing_agent_using_units")
    private Integer productPnIdFusingAgentUsingUnits;

    @TableField("product_pn_ID_bright_fusing_agent_using_units")
    private Integer productPnIdBrightFusingAgentUsingUnits;

    @TableField("product_pn_ID_magenta_agent_using_units")
    private Integer productPnIdMagentaAgentUsingUnits;

    @TableField("product_pn_ID_cyan_agent_using_units")
    private Integer productPnIdCyanAgentUsingUnits;

    @TableField("product_pn_ID_end_tem")
    private Double productPnIdEndTem;

    @TableField("product_pn_ID_end_hum")
    private Double productPnIdEndHum;

    @TableField("product_pn_ID_acc")
    private Double productPnIdAcc;

    @TableField("product_pn_ID_color")
    private String productPnIdColor;

    @TableField("product_pn_ID_cold_start_time")
    private LocalDateTime productPnIdColdStartTime;

    @TableField("product_pn_ID_cold_end_time")
    private LocalDateTime productPnIdColdEndTime;


    public Integer getPnidParameterId() {
        return pnidParameterId;
    }

    public void setPnidParameterId(Integer pnidParameterId) {
        this.pnidParameterId = pnidParameterId;
    }

    public String getPurchaseOrderProductName() {
        return purchaseOrderProductName;
    }

    public void setPurchaseOrderProductName(String purchaseOrderProductName) {
        this.purchaseOrderProductName = purchaseOrderProductName;
    }

    public Double getProductLength() {
        return productLength;
    }

    public void setProductLength(Double productLength) {
        this.productLength = productLength;
    }

    public Double getProductWidth() {
        return productWidth;
    }

    public void setProductWidth(Double productWidth) {
        this.productWidth = productWidth;
    }

    public Double getProductHeight() {
        return productHeight;
    }

    public void setProductHeight(Double productHeight) {
        this.productHeight = productHeight;
    }

    public String getPurchaseOrderProductPn() {
        return purchaseOrderProductPn;
    }

    public void setPurchaseOrderProductPn(String purchaseOrderProductPn) {
        this.purchaseOrderProductPn = purchaseOrderProductPn;
    }

    public Integer getPurchaseOrderProductPnId() {
        return purchaseOrderProductPnId;
    }

    public void setPurchaseOrderProductPnId(Integer purchaseOrderProductPnId) {
        this.purchaseOrderProductPnId = purchaseOrderProductPnId;
    }

    public LocalDateTime getProductPnIdBeginingDatetime() {
        return productPnIdBeginingDatetime;
    }

    public void setProductPnIdBeginingDatetime(LocalDateTime productPnIdBeginingDatetime) {
        this.productPnIdBeginingDatetime = productPnIdBeginingDatetime;
    }

    public LocalDateTime getProductPnIdEndDatetime() {
        return productPnIdEndDatetime;
    }

    public void setProductPnIdEndDatetime(LocalDateTime productPnIdEndDatetime) {
        this.productPnIdEndDatetime = productPnIdEndDatetime;
    }

    public Integer getProductPnIdCompletedPercentDatetime() {
        return productPnIdCompletedPercentDatetime;
    }

    public void setProductPnIdCompletedPercentDatetime(Integer productPnIdCompletedPercentDatetime) {
        this.productPnIdCompletedPercentDatetime = productPnIdCompletedPercentDatetime;
    }

    public Double getProductPnIdMaterialUsingUnits() {
        return productPnIdMaterialUsingUnits;
    }

    public void setProductPnIdMaterialUsingUnits(Double productPnIdMaterialUsingUnits) {
        this.productPnIdMaterialUsingUnits = productPnIdMaterialUsingUnits;
    }

    public Integer getProductPnIdBlackAgentUsingUnits() {
        return productPnIdBlackAgentUsingUnits;
    }

    public void setProductPnIdBlackAgentUsingUnits(Integer productPnIdBlackAgentUsingUnits) {
        this.productPnIdBlackAgentUsingUnits = productPnIdBlackAgentUsingUnits;
    }

    public Integer getProductPnIdYellowAgentUsingUnits() {
        return productPnIdYellowAgentUsingUnits;
    }

    public void setProductPnIdYellowAgentUsingUnits(Integer productPnIdYellowAgentUsingUnits) {
        this.productPnIdYellowAgentUsingUnits = productPnIdYellowAgentUsingUnits;
    }

    public Integer getProductPnIdDetailingAgentUsingUnits() {
        return productPnIdDetailingAgentUsingUnits;
    }

    public void setProductPnIdDetailingAgentUsingUnits(Integer productPnIdDetailingAgentUsingUnits) {
        this.productPnIdDetailingAgentUsingUnits = productPnIdDetailingAgentUsingUnits;
    }

    public Integer getProductPnIdFusingAgentUsingUnits() {
        return productPnIdFusingAgentUsingUnits;
    }

    public void setProductPnIdFusingAgentUsingUnits(Integer productPnIdFusingAgentUsingUnits) {
        this.productPnIdFusingAgentUsingUnits = productPnIdFusingAgentUsingUnits;
    }

    public Integer getProductPnIdBrightFusingAgentUsingUnits() {
        return productPnIdBrightFusingAgentUsingUnits;
    }

    public void setProductPnIdBrightFusingAgentUsingUnits(Integer productPnIdBrightFusingAgentUsingUnits) {
        this.productPnIdBrightFusingAgentUsingUnits = productPnIdBrightFusingAgentUsingUnits;
    }

    public Integer getProductPnIdMagentaAgentUsingUnits() {
        return productPnIdMagentaAgentUsingUnits;
    }

    public void setProductPnIdMagentaAgentUsingUnits(Integer productPnIdMagentaAgentUsingUnits) {
        this.productPnIdMagentaAgentUsingUnits = productPnIdMagentaAgentUsingUnits;
    }

    public Integer getProductPnIdCyanAgentUsingUnits() {
        return productPnIdCyanAgentUsingUnits;
    }

    public void setProductPnIdCyanAgentUsingUnits(Integer productPnIdCyanAgentUsingUnits) {
        this.productPnIdCyanAgentUsingUnits = productPnIdCyanAgentUsingUnits;
    }

    public Double getProductPnIdEndTem() {
        return productPnIdEndTem;
    }

    public void setProductPnIdEndTem(Double productPnIdEndTem) {
        this.productPnIdEndTem = productPnIdEndTem;
    }

    public Double getProductPnIdEndHum() {
        return productPnIdEndHum;
    }

    public void setProductPnIdEndHum(Double productPnIdEndHum) {
        this.productPnIdEndHum = productPnIdEndHum;
    }

    public Double getProductPnIdAcc() {
        return productPnIdAcc;
    }

    public void setProductPnIdAcc(Double productPnIdAcc) {
        this.productPnIdAcc = productPnIdAcc;
    }

    public String getProductPnIdColor() {
        return productPnIdColor;
    }

    public void setProductPnIdColor(String productPnIdColor) {
        this.productPnIdColor = productPnIdColor;
    }

    public LocalDateTime getProductPnIdColdStartTime() {
        return productPnIdColdStartTime;
    }

    public void setProductPnIdColdStartTime(LocalDateTime productPnIdColdStartTime) {
        this.productPnIdColdStartTime = productPnIdColdStartTime;
    }

    public LocalDateTime getProductPnIdColdEndTime() {
        return productPnIdColdEndTime;
    }

    public void setProductPnIdColdEndTime(LocalDateTime productPnIdColdEndTime) {
        this.productPnIdColdEndTime = productPnIdColdEndTime;
    }

    @Override
    public String toString() {
        return "PnidParameterTb{" +
        "pnidParameterId=" + pnidParameterId +
        ", purchaseOrderProductName=" + purchaseOrderProductName +
        ", productLength=" + productLength +
        ", productWidth=" + productWidth +
        ", productHeight=" + productHeight +
        ", purchaseOrderProductPn=" + purchaseOrderProductPn +
        ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        ", productPnIdBeginingDatetime=" + productPnIdBeginingDatetime +
        ", productPnIdEndDatetime=" + productPnIdEndDatetime +
        ", productPnIdCompletedPercentDatetime=" + productPnIdCompletedPercentDatetime +
        ", productPnIdMaterialUsingUnits=" + productPnIdMaterialUsingUnits +
        ", productPnIdBlackAgentUsingUnits=" + productPnIdBlackAgentUsingUnits +
        ", productPnIdYellowAgentUsingUnits=" + productPnIdYellowAgentUsingUnits +
        ", productPnIdDetailingAgentUsingUnits=" + productPnIdDetailingAgentUsingUnits +
        ", productPnIdFusingAgentUsingUnits=" + productPnIdFusingAgentUsingUnits +
        ", productPnIdBrightFusingAgentUsingUnits=" + productPnIdBrightFusingAgentUsingUnits +
        ", productPnIdMagentaAgentUsingUnits=" + productPnIdMagentaAgentUsingUnits +
        ", productPnIdCyanAgentUsingUnits=" + productPnIdCyanAgentUsingUnits +
        ", productPnIdEndTem=" + productPnIdEndTem +
        ", productPnIdEndHum=" + productPnIdEndHum +
        ", productPnIdAcc=" + productPnIdAcc +
        ", productPnIdColor=" + productPnIdColor +
        ", productPnIdColdStartTime=" + productPnIdColdStartTime +
        ", productPnIdColdEndTime=" + productPnIdColdEndTime +
        "}";
    }
}
