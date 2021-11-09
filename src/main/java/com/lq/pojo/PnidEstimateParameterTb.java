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
//打印机预估参数表
@TableName("PNID_estimate_parameter_tb")
public class PnidEstimateParameterTb implements Serializable {

    private static final long serialVersionUID = 294190550911881296L;
    @TableId(value = "PNID_estimate_parameter_ID", type = IdType.AUTO)
    private Integer pnidEstimateParameterId;

    private String purchaseOrderProductName;

    private Double productEstimateLength;

    private Double productEstimateWidth;

    private Double productEstimateHeight;

    private String purchaseOrderProductPn;

    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;

    @TableField("product_pn_ID_estimate_end_datetime")
    private LocalDateTime productPnIdEstimateEndDatetime;

    @TableField("product_pn_ID_material_estimate_units")
    private Double productPnIdMaterialEstimateUnits;

    @TableField("product_pn_ID_black_agent_estimate_units")
    private Integer productPnIdBlackAgentEstimateUnits;

    @TableField("product_pn_ID_yellow_agent_estimate_units")
    private Integer productPnIdYellowAgentEstimateUnits;

    @TableField("product_pn_ID_detailing_agent_estimate_units")
    private Integer productPnIdDetailingAgentEstimateUnits;

    @TableField("product_pn_ID_fusing_agent_estimate_units")
    private Integer productPnIdFusingAgentEstimateUnits;

    @TableField("product_pn_ID_bright_fusing_agent_estimate_units")
    private Integer productPnIdBrightFusingAgentEstimateUnits;

    @TableField("product_pn_ID_magenta_agent_estimate_units")
    private Integer productPnIdMagentaAgentEstimateUnits;

    @TableField("product_pn_ID_cyan_agent_estimate_units")
    private Integer productPnIdCyanAgentEstimateUnits;

    @TableField("product_pn_ID_start_tem")
    private Double productPnIdStartTem;

    @TableField("product_pn_ID_start_hum")
    private Double productPnIdStartHum;

    @TableField("product_pn_ID_estimate_acc")
    private Double productPnIdEstimateAcc;

    @TableField("product_pn_ID_estimate_color")
    private String productPnIdEstimateColor;

    @TableField("product_pn_ID_cold_estimate_start_time")
    private LocalDateTime productPnIdColdEstimateStartTime;

    @TableField("product_pn_ID_cold_estimate_end_time")
    private LocalDateTime productPnIdColdEstimateEndTime;


    public Integer getPnidEstimateParameterId() {
        return pnidEstimateParameterId;
    }

    public void setPnidEstimateParameterId(Integer pnidEstimateParameterId) {
        this.pnidEstimateParameterId = pnidEstimateParameterId;
    }

    public String getPurchaseOrderProductName() {
        return purchaseOrderProductName;
    }

    public void setPurchaseOrderProductName(String purchaseOrderProductName) {
        this.purchaseOrderProductName = purchaseOrderProductName;
    }

    public Double getProductEstimateLength() {
        return productEstimateLength;
    }

    public void setProductEstimateLength(Double productEstimateLength) {
        this.productEstimateLength = productEstimateLength;
    }

    public Double getProductEstimateWidth() {
        return productEstimateWidth;
    }

    public void setProductEstimateWidth(Double productEstimateWidth) {
        this.productEstimateWidth = productEstimateWidth;
    }

    public Double getProductEstimateHeight() {
        return productEstimateHeight;
    }

    public void setProductEstimateHeight(Double productEstimateHeight) {
        this.productEstimateHeight = productEstimateHeight;
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

    public LocalDateTime getProductPnIdEstimateEndDatetime() {
        return productPnIdEstimateEndDatetime;
    }

    public void setProductPnIdEstimateEndDatetime(LocalDateTime productPnIdEstimateEndDatetime) {
        this.productPnIdEstimateEndDatetime = productPnIdEstimateEndDatetime;
    }

    public Double getProductPnIdMaterialEstimateUnits() {
        return productPnIdMaterialEstimateUnits;
    }

    public void setProductPnIdMaterialEstimateUnits(Double productPnIdMaterialEstimateUnits) {
        this.productPnIdMaterialEstimateUnits = productPnIdMaterialEstimateUnits;
    }

    public Integer getProductPnIdBlackAgentEstimateUnits() {
        return productPnIdBlackAgentEstimateUnits;
    }

    public void setProductPnIdBlackAgentEstimateUnits(Integer productPnIdBlackAgentEstimateUnits) {
        this.productPnIdBlackAgentEstimateUnits = productPnIdBlackAgentEstimateUnits;
    }

    public Integer getProductPnIdYellowAgentEstimateUnits() {
        return productPnIdYellowAgentEstimateUnits;
    }

    public void setProductPnIdYellowAgentEstimateUnits(Integer productPnIdYellowAgentEstimateUnits) {
        this.productPnIdYellowAgentEstimateUnits = productPnIdYellowAgentEstimateUnits;
    }

    public Integer getProductPnIdDetailingAgentEstimateUnits() {
        return productPnIdDetailingAgentEstimateUnits;
    }

    public void setProductPnIdDetailingAgentEstimateUnits(Integer productPnIdDetailingAgentEstimateUnits) {
        this.productPnIdDetailingAgentEstimateUnits = productPnIdDetailingAgentEstimateUnits;
    }

    public Integer getProductPnIdFusingAgentEstimateUnits() {
        return productPnIdFusingAgentEstimateUnits;
    }

    public void setProductPnIdFusingAgentEstimateUnits(Integer productPnIdFusingAgentEstimateUnits) {
        this.productPnIdFusingAgentEstimateUnits = productPnIdFusingAgentEstimateUnits;
    }

    public Integer getProductPnIdBrightFusingAgentEstimateUnits() {
        return productPnIdBrightFusingAgentEstimateUnits;
    }

    public void setProductPnIdBrightFusingAgentEstimateUnits(Integer productPnIdBrightFusingAgentEstimateUnits) {
        this.productPnIdBrightFusingAgentEstimateUnits = productPnIdBrightFusingAgentEstimateUnits;
    }

    public Integer getProductPnIdMagentaAgentEstimateUnits() {
        return productPnIdMagentaAgentEstimateUnits;
    }

    public void setProductPnIdMagentaAgentEstimateUnits(Integer productPnIdMagentaAgentEstimateUnits) {
        this.productPnIdMagentaAgentEstimateUnits = productPnIdMagentaAgentEstimateUnits;
    }

    public Integer getProductPnIdCyanAgentEstimateUnits() {
        return productPnIdCyanAgentEstimateUnits;
    }

    public void setProductPnIdCyanAgentEstimateUnits(Integer productPnIdCyanAgentEstimateUnits) {
        this.productPnIdCyanAgentEstimateUnits = productPnIdCyanAgentEstimateUnits;
    }

    public Double getProductPnIdStartTem() {
        return productPnIdStartTem;
    }

    public void setProductPnIdStartTem(Double productPnIdStartTem) {
        this.productPnIdStartTem = productPnIdStartTem;
    }

    public Double getProductPnIdStartHum() {
        return productPnIdStartHum;
    }

    public void setProductPnIdStartHum(Double productPnIdStartHum) {
        this.productPnIdStartHum = productPnIdStartHum;
    }

    public Double getProductPnIdEstimateAcc() {
        return productPnIdEstimateAcc;
    }

    public void setProductPnIdEstimateAcc(Double productPnIdEstimateAcc) {
        this.productPnIdEstimateAcc = productPnIdEstimateAcc;
    }

    public String getProductPnIdEstimateColor() {
        return productPnIdEstimateColor;
    }

    public void setProductPnIdEstimateColor(String productPnIdEstimateColor) {
        this.productPnIdEstimateColor = productPnIdEstimateColor;
    }

    public LocalDateTime getProductPnIdColdEstimateStartTime() {
        return productPnIdColdEstimateStartTime;
    }

    public void setProductPnIdColdEstimateStartTime(LocalDateTime productPnIdColdEstimateStartTime) {
        this.productPnIdColdEstimateStartTime = productPnIdColdEstimateStartTime;
    }

    public LocalDateTime getProductPnIdColdEstimateEndTime() {
        return productPnIdColdEstimateEndTime;
    }

    public void setProductPnIdColdEstimateEndTime(LocalDateTime productPnIdColdEstimateEndTime) {
        this.productPnIdColdEstimateEndTime = productPnIdColdEstimateEndTime;
    }

    @Override
    public String toString() {
        return "PnidEstimateParameterTb{" +
        "pnidEstimateParameterId=" + pnidEstimateParameterId +
        ", purchaseOrderProductName=" + purchaseOrderProductName +
        ", productEstimateLength=" + productEstimateLength +
        ", productEstimateWidth=" + productEstimateWidth +
        ", productEstimateHeight=" + productEstimateHeight +
        ", purchaseOrderProductPn=" + purchaseOrderProductPn +
        ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        ", productPnIdEstimateEndDatetime=" + productPnIdEstimateEndDatetime +
        ", productPnIdMaterialEstimateUnits=" + productPnIdMaterialEstimateUnits +
        ", productPnIdBlackAgentEstimateUnits=" + productPnIdBlackAgentEstimateUnits +
        ", productPnIdYellowAgentEstimateUnits=" + productPnIdYellowAgentEstimateUnits +
        ", productPnIdDetailingAgentEstimateUnits=" + productPnIdDetailingAgentEstimateUnits +
        ", productPnIdFusingAgentEstimateUnits=" + productPnIdFusingAgentEstimateUnits +
        ", productPnIdBrightFusingAgentEstimateUnits=" + productPnIdBrightFusingAgentEstimateUnits +
        ", productPnIdMagentaAgentEstimateUnits=" + productPnIdMagentaAgentEstimateUnits +
        ", productPnIdCyanAgentEstimateUnits=" + productPnIdCyanAgentEstimateUnits +
        ", productPnIdStartTem=" + productPnIdStartTem +
        ", productPnIdStartHum=" + productPnIdStartHum +
        ", productPnIdEstimateAcc=" + productPnIdEstimateAcc +
        ", productPnIdEstimateColor=" + productPnIdEstimateColor +
        ", productPnIdColdEstimateStartTime=" + productPnIdColdEstimateStartTime +
        ", productPnIdColdEstimateEndTime=" + productPnIdColdEstimateEndTime +
        "}";
    }
}
