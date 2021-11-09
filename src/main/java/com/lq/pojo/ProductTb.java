package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
//打印物品表
@Data
public class ProductTb implements Serializable {

    private static final long serialVersionUID = -8685263980754392771L;

    @TableId(value = "product_Id", type = IdType.AUTO)
    private Integer productId;

    private String purchaseOrderNumber;

    private String purchaseOrderProductPn;

    private Integer purchaseOrderProductPnId;

    private String purchaseOrderProductName;

    private Integer purchaseOrderProductTotalQty;

    private String printerModelRequestCustomer;

    private String factoryMaterialName;

    private String colorRequestCustomer;

    private Double productLengthRequestCustomer;

    private Double productWidthRequestCustomer;

    private Double productHeightRequestCustomer;

    private Double productAccRequestCustomer;

    private Double productWeightRequestCustomer;

    @TableField("factory_material_PN")
    private String factoryMaterialPn;

    private String shopAddr;

    private String description;

    private String homeAddr;

    private String downloadAddr;

    private String logisticName;

    private String logistic;

    private String logisticTime;
    @TableField("customer_cart_ID")
    private Integer customerCartID;

    private String shouHou;

    private List<PurchaseOrderTb> purchaseOrderTb;

    private List<CartImgTb> cartImgTb;

    private List<BuyNowImgTb> buyNowImgTb;

    private List<CustomOrderTb> customOrderTb;

    public String getLogisticName() {
        return logisticName;
    }

    public void setLogisticName(String logisticName) {
        this.logisticName = logisticName;
    }

    public String getLogistic() {
        return logistic;
    }

    public void setLogistic(String logistic) {
        this.logistic = logistic;
    }

    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public String getDownloadAddr() {
        return downloadAddr;
    }

    public void setDownloadAddr(String downloadAddr) {
        this.downloadAddr = downloadAddr;
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

    public String getPurchaseOrderProductName() {
        return purchaseOrderProductName;
    }

    public void setPurchaseOrderProductName(String purchaseOrderProductName) {
        this.purchaseOrderProductName = purchaseOrderProductName;
    }

    public Integer getPurchaseOrderProductTotalQty() {
        return purchaseOrderProductTotalQty;
    }

    public void setPurchaseOrderProductTotalQty(Integer purchaseOrderProductTotalQty) {
        this.purchaseOrderProductTotalQty = purchaseOrderProductTotalQty;
    }

    public String getPrinterModelRequestCustomer() {
        return printerModelRequestCustomer;
    }

    public void setPrinterModelRequestCustomer(String printerModelRequestCustomer) {
        this.printerModelRequestCustomer = printerModelRequestCustomer;
    }

    public String getFactoryMaterialName() {
        return factoryMaterialName;
    }

    public void setFactoryMaterialName(String factoryMaterialName) {
        this.factoryMaterialName = factoryMaterialName;
    }

    public String getColorRequestCustomer() {
        return colorRequestCustomer;
    }

    public void setColorRequestCustomer(String colorRequestCustomer) {
        this.colorRequestCustomer = colorRequestCustomer;
    }

    public Double getProductLengthRequestCustomer() {
        return productLengthRequestCustomer;
    }

    public void setProductLengthRequestCustomer(Double productLengthRequestCustomer) {
        this.productLengthRequestCustomer = productLengthRequestCustomer;
    }

    public Double getProductWidthRequestCustomer() {
        return productWidthRequestCustomer;
    }

    public void setProductWidthRequestCustomer(Double productWidthRequestCustomer) {
        this.productWidthRequestCustomer = productWidthRequestCustomer;
    }

    public Double getProductHeightRequestCustomer() {
        return productHeightRequestCustomer;
    }

    public void setProductHeightRequestCustomer(Double productHeightRequestCustomer) {
        this.productHeightRequestCustomer = productHeightRequestCustomer;
    }

    public Double getProductAccRequestCustomer() {
        return productAccRequestCustomer;
    }

    public void setProductAccRequestCustomer(Double productAccRequestCustomer) {
        this.productAccRequestCustomer = productAccRequestCustomer;
    }

    public Double getProductWeightRequestCustomer() {
        return productWeightRequestCustomer;
    }

    public void setProductWeightRequestCustomer(Double productWeightRequestCustomer) {
        this.productWeightRequestCustomer = productWeightRequestCustomer;
    }

    public String getFactoryMaterialPn() {
        return factoryMaterialPn;
    }

    public void setFactoryMaterialPn(String factoryMaterialPn) {
        this.factoryMaterialPn = factoryMaterialPn;
    }


    @Override
    public String toString() {
        return "ProductTb{" +
                ", purchaseOrderNumber=" + purchaseOrderNumber +
                ", productId=" + productId +
                ", purchaseOrderProductPn=" + purchaseOrderProductPn +
                ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
                ", purchaseOrderProductName=" + purchaseOrderProductName +
                ", purchaseOrderProductTotalQty=" + purchaseOrderProductTotalQty +
                ", printerModelRequestCustomer=" + printerModelRequestCustomer +
                ", factoryMaterialName=" + factoryMaterialName +
                ", colorRequestCustomer=" + colorRequestCustomer +
                ", productLengthRequestCustomer=" + productLengthRequestCustomer +
                ", productWidthRequestCustomer=" + productWidthRequestCustomer +
                ", productHeightRequestCustomer=" + productHeightRequestCustomer +
                ", productAccRequestCustomer=" + productAccRequestCustomer +
                ", productWeightRequestCustomer=" + productWeightRequestCustomer +
                ", factoryMaterialPn=" + factoryMaterialPn +
                "}";
    }

}
