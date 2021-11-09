package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-07-27
 */
@Data
public class NfcTb implements Serializable {


    private static final long serialVersionUID = 3591344903717035868L;
    @TableId(value = "nfc_Id", type = IdType.AUTO)
    private Integer nfcId;

    private String nfcUrl;

    private String nfcPwd;

    private Integer productId;

    private String purchaseOrderProductName;

    private String nfcScan;

    private String date;

    private String nfcImgUrl;

    private String nfcWord;

    private Integer accountId;

    private String purchaseOrderNumber;

    private String corporateName;

    private String companySlogan;

    private String companyBackground;

    public Integer getNfcId() {
        return nfcId;
    }

    public void setNfcId(Integer nfcId) {
        this.nfcId = nfcId;
    }

    public String getNfcUrl() {
        return nfcUrl;
    }

    public void setNfcUrl(String nfcUrl) {
        this.nfcUrl = nfcUrl;
    }

    public String getNfcPwd() {
        return nfcPwd;
    }

    public void setNfcPwd(String nfcPwd) {
        this.nfcPwd = nfcPwd;
    }

    @Override
    public String toString() {
        return "NfcTb{" +
        "nfcId=" + nfcId +
        ", nfcUrl=" + nfcUrl +
        ", nfcPwd=" + nfcPwd +
        "}";
    }
}
