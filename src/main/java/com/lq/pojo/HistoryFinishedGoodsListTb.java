package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-05-11
 */
@Data
public class HistoryFinishedGoodsListTb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;

    private String finishedGoodsName;

    private String purchaseOrderProductPn;

    private String finishedGoodsDescription;

    private Double finishedGoodsLength;

    private Double finishedGoodsWidth;

    private Double finishedGoodsHeight;

    private Double finishedGoodsAcc;

    private Double finishedGoodsWeight;

    private String finishedGoodsTexture;

    private String finishedGoodsCertificatioin;

    private Integer finishedGoodsPrintTimes;

    private String finishedGoodsPatent;

    private String finishedGoodsPatentNumber;

    @TableField("account_ID")
    private Integer accountId;

    @TableField("manufacture_ID")
    private Integer manufactureId;

    private String finishedGoodsIssueDatetime;

    private Double finishedGoodsCustomerPriced;

    private Double finishedGoodsManufacturePrice;

    private String homePageAddr;

    private String drawingFilesAddr;

    @TableField("factory_material_PN")
    private String factoryMaterialPn;

    private String finishedGoodsColor;

    private Double finishedGoodsVolume;

    private Integer finishedGoodsPrintTime;

    private String printerModelRequestCustomer;

    private Integer diy;

    private Integer xiaJia;

    private Integer bz;

    private Integer numImg;

    private String imgBz;

    private String popupImg;

    private Integer numberOfVisits;


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

    public String getPurchaseOrderProductPn() {
        return purchaseOrderProductPn;
    }

    public void setPurchaseOrderProductPn(String purchaseOrderProductPn) {
        this.purchaseOrderProductPn = purchaseOrderProductPn;
    }

    public String getFinishedGoodsDescription() {
        return finishedGoodsDescription;
    }

    public void setFinishedGoodsDescription(String finishedGoodsDescription) {
        this.finishedGoodsDescription = finishedGoodsDescription;
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

    public Integer getFinishedGoodsPrintTimes() {
        return finishedGoodsPrintTimes;
    }

    public void setFinishedGoodsPrintTimes(Integer finishedGoodsPrintTimes) {
        this.finishedGoodsPrintTimes = finishedGoodsPrintTimes;
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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(Integer manufactureId) {
        this.manufactureId = manufactureId;
    }

    public String getFinishedGoodsIssueDatetime() {
        return finishedGoodsIssueDatetime;
    }

    public void setFinishedGoodsIssueDatetime(String finishedGoodsIssueDatetime) {
        this.finishedGoodsIssueDatetime = finishedGoodsIssueDatetime;
    }

    public Double getFinishedGoodsCustomerPriced() {
        return finishedGoodsCustomerPriced;
    }

    public void setFinishedGoodsCustomerPriced(Double finishedGoodsCustomerPriced) {
        this.finishedGoodsCustomerPriced = finishedGoodsCustomerPriced;
    }

    public Double getFinishedGoodsManufacturePrice() {
        return finishedGoodsManufacturePrice;
    }

    public void setFinishedGoodsManufacturePrice(Double finishedGoodsManufacturePrice) {
        this.finishedGoodsManufacturePrice = finishedGoodsManufacturePrice;
    }

    public String getHomePageAddr() {
        return homePageAddr;
    }

    public void setHomePageAddr(String homePageAddr) {
        this.homePageAddr = homePageAddr;
    }

    public String getDrawingFilesAddr() {
        return drawingFilesAddr;
    }

    public void setDrawingFilesAddr(String drawingFilesAddr) {
        this.drawingFilesAddr = drawingFilesAddr;
    }

    public String getFactoryMaterialPn() {
        return factoryMaterialPn;
    }

    public void setFactoryMaterialPn(String factoryMaterialPn) {
        this.factoryMaterialPn = factoryMaterialPn;
    }

    public String getFinishedGoodsColor() {
        return finishedGoodsColor;
    }

    public void setFinishedGoodsColor(String finishedGoodsColor) {
        this.finishedGoodsColor = finishedGoodsColor;
    }


    public Double getFinishedGoodsVolume() {
        return finishedGoodsVolume;
    }

    public void setFinishedGoodsVolume(Double finishedGoodsVolume) {
        this.finishedGoodsVolume = finishedGoodsVolume;
    }

    public Integer getFinishedGoodsPrintTime() {
        return finishedGoodsPrintTime;
    }

    public void setFinishedGoodsPrintTime(Integer finishedGoodsPrintTime) {
        this.finishedGoodsPrintTime = finishedGoodsPrintTime;
    }

    public String getPrinterModelRequestCustomer() {
        return printerModelRequestCustomer;
    }

    public void setPrinterModelRequestCustomer(String printerModelRequestCustomer) {
        this.printerModelRequestCustomer = printerModelRequestCustomer;
    }

    public Integer getDiy() {
        return diy;
    }

    public void setDiy(Integer diy) {
        this.diy = diy;
    }

    @Override
    public String toString() {
        return "HistoryFinishedGoodsListTb{" +
        "purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        ", finishedGoodsName=" + finishedGoodsName +
        ", purchaseOrderProductPn=" + purchaseOrderProductPn +
        ", finishedGoodsDescription=" + finishedGoodsDescription +
        ", finishedGoodsLength=" + finishedGoodsLength +
        ", finishedGoodsWidth=" + finishedGoodsWidth +
        ", finishedGoodsHeight=" + finishedGoodsHeight +
        ", finishedGoodsAcc=" + finishedGoodsAcc +
        ", finishedGoodsWeight=" + finishedGoodsWeight +
        ", finishedGoodsTexture=" + finishedGoodsTexture +
        ", finishedGoodsCertificatioin=" + finishedGoodsCertificatioin +
        ", finishedGoodsPrintTimes=" + finishedGoodsPrintTimes +
        ", finishedGoodsPatent=" + finishedGoodsPatent +
        ", finishedGoodsPatentNumber=" + finishedGoodsPatentNumber +
        ", accountId=" + accountId +
        ", manufactureId=" + manufactureId +
        ", finishedGoodsIssueDatetime=" + finishedGoodsIssueDatetime +
        ", finishedGoodsCustomerPriced=" + finishedGoodsCustomerPriced +
        ", finishedGoodsManufacturePrice=" + finishedGoodsManufacturePrice +
        ", homePageAddr=" + homePageAddr +
        ", drawingFilesAddr=" + drawingFilesAddr +
        ", factoryMaterialPn=" + factoryMaterialPn +
        ", finishedGoodsColor=" + finishedGoodsColor +
        ", finishedGoodsVolume=" + finishedGoodsVolume +
        ", finishedGoodsPrintTime=" + finishedGoodsPrintTime +
        ", printerModelRequestCustomer=" + printerModelRequestCustomer +
        ", diy=" + diy +
        "}";
    }
}
