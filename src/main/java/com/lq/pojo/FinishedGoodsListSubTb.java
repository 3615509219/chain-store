package com.lq.pojo;

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
 * @since 2021-08-26
 */
@Data
public class FinishedGoodsListSubTb implements Serializable {


    private static final long serialVersionUID = 4863523298523872014L;
    @TableId(value = "finished_goods_list_sub_ID", type = IdType.AUTO)
    private Integer finishedGoodsListSubId;
    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;
    private String purchaseOrderProductPn;
    private String finishedGoodsName;
    private Double finishedGoodsLength;
    private Double finishedGoodsWidth;
    private Double finishedGoodsHeight;
    private Double finishedGoodsAcc;
    private Double finishedGoodsWeight;
    private String finishedGoodsTexture;
    private String finishedGoodsCertificatioin;
    private String finishedGoodsPatent;
    private String finishedGoodsPatentNumber;
    private Integer finishedGoodsPrintTime;
    private Double finishedGoodsVolume;
    private String finishedGoodsColor;
    private Double finishedGoodsCustomerPriced;
    private Double finishedGoodsManufacturePrice;
    private String drawingFilesAddr;
    private String homePageAddr;
    private String time;


    public Integer getFinishedGoodsListSubId() {
        return finishedGoodsListSubId;
    }

    public void setFinishedGoodsListSubId(Integer finishedGoodsListSubId) {
        this.finishedGoodsListSubId = finishedGoodsListSubId;
    }

    public Integer getPurchaseOrderProductPnId() {
        return purchaseOrderProductPnId;
    }

    public void setPurchaseOrderProductPnId(Integer purchaseOrderProductPnId) {
        this.purchaseOrderProductPnId = purchaseOrderProductPnId;
    }

    public String getFinishedGoodsName() {
        return finishedGoodsName;
    }

    public void setFinishedGoodsName(String finishedGoodsName) {
        this.finishedGoodsName = finishedGoodsName;
    }

    public Double getFinishedGoodsLength() {
        return finishedGoodsLength;
    }

    public void setFinishedGoodsLength(Double finishedGoodsLength) {
        this.finishedGoodsLength = finishedGoodsLength;
    }

    public Double getFinishedGoodsWidth() {
        return finishedGoodsWidth;
    }

    public void setFinishedGoodsWidth(Double finishedGoodsWidth) {
        this.finishedGoodsWidth = finishedGoodsWidth;
    }

    public Double getFinishedGoodsHeight() {
        return finishedGoodsHeight;
    }

    public void setFinishedGoodsHeight(Double finishedGoodsHeight) {
        this.finishedGoodsHeight = finishedGoodsHeight;
    }

    public Double getFinishedGoodsAcc() {
        return finishedGoodsAcc;
    }

    public void setFinishedGoodsAcc(Double finishedGoodsAcc) {
        this.finishedGoodsAcc = finishedGoodsAcc;
    }

    public Double getFinishedGoodsWeight() {
        return finishedGoodsWeight;
    }

    public void setFinishedGoodsWeight(Double finishedGoodsWeight) {
        this.finishedGoodsWeight = finishedGoodsWeight;
    }

    public String getFinishedGoodsTexture() {
        return finishedGoodsTexture;
    }

    public void setFinishedGoodsTexture(String finishedGoodsTexture) {
        this.finishedGoodsTexture = finishedGoodsTexture;
    }

    public String getFinishedGoodsCertificatioin() {
        return finishedGoodsCertificatioin;
    }

    public void setFinishedGoodsCertificatioin(String finishedGoodsCertificatioin) {
        this.finishedGoodsCertificatioin = finishedGoodsCertificatioin;
    }

    public String getFinishedGoodsPatent() {
        return finishedGoodsPatent;
    }

    public void setFinishedGoodsPatent(String finishedGoodsPatent) {
        this.finishedGoodsPatent = finishedGoodsPatent;
    }

    public String getFinishedGoodsPatentNumber() {
        return finishedGoodsPatentNumber;
    }

    public void setFinishedGoodsPatentNumber(String finishedGoodsPatentNumber) {
        this.finishedGoodsPatentNumber = finishedGoodsPatentNumber;
    }

    public Integer getFinishedGoodsPrintTime() {
        return finishedGoodsPrintTime;
    }

    public void setFinishedGoodsPrintTime(Integer finishedGoodsPrintTime) {
        this.finishedGoodsPrintTime = finishedGoodsPrintTime;
    }

    public Double getFinishedGoodsVolume() {
        return finishedGoodsVolume;
    }

    public void setFinishedGoodsVolume(Double finishedGoodsVolume) {
        this.finishedGoodsVolume = finishedGoodsVolume;
    }

    public String getFinishedGoodsColor() {
        return finishedGoodsColor;
    }

    public void setFinishedGoodsColor(String finishedGoodsColor) {
        this.finishedGoodsColor = finishedGoodsColor;
    }

    public Double getFinishedGoodsCustomerPriced() {
        return finishedGoodsCustomerPriced;
    }

    public void setFinishedGoodsCustomerPriced(Double finishedGoodsCustomerPrice) {
        this.finishedGoodsCustomerPriced = finishedGoodsCustomerPrice;
    }

    public Double getFinishedGoodsManufacturePrice() {
        return finishedGoodsManufacturePrice;
    }

    public void setFinishedGoodsManufacturePrice(Double finishedGoodsManufacturePrice) {
        this.finishedGoodsManufacturePrice = finishedGoodsManufacturePrice;
    }

    public String getDrawingFilesAddr() {
        return drawingFilesAddr;
    }

    public void setDrawingFilesAddr(String drawingFilesAddr) {
        this.drawingFilesAddr = drawingFilesAddr;
    }

    @Override
    public String toString() {
        return "FinishedGoodsListSubTb{" +
        "finishedGoodsListSubId=" + finishedGoodsListSubId +
        ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        ", finishedGoodsName=" + finishedGoodsName +
        ", finishedGoodsLength=" + finishedGoodsLength +
        ", finishedGoodsWidth=" + finishedGoodsWidth +
        ", finishedGoodsHeight=" + finishedGoodsHeight +
        ", finishedGoodsAcc=" + finishedGoodsAcc +
        ", finishedGoodsWeight=" + finishedGoodsWeight +
        ", finishedGoodsTexture=" + finishedGoodsTexture +
        ", finishedGoodsCertificatioin=" + finishedGoodsCertificatioin +
        ", finishedGoodsPatent=" + finishedGoodsPatent +
        ", finishedGoodsPatentNumber=" + finishedGoodsPatentNumber +
        ", finishedGoodsPrintTime=" + finishedGoodsPrintTime +
        ", finishedGoodsVolume=" + finishedGoodsVolume +
        ", finishedGoodsColor=" + finishedGoodsColor +
        ", finishedGoodsCustomerPriced=" + finishedGoodsCustomerPriced +
        ", finishedGoodsManufacturePrice=" + finishedGoodsManufacturePrice +
        ", drawingFilesAddr=" + drawingFilesAddr +
        "}";
    }
}
