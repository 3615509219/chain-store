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
 * @since 2021-05-08
 */
@Data
public class PersonRegisterBankTb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bank_Id", type = IdType.AUTO)
    private Integer bankId;

    @TableField("account_ID")
    private Integer accountId;

    private String name;

    private String bankName;

    @TableField("card_Id")
    private String cardId;

    private String cardName;

    private String province;

    private String city;

    private String tel;

    private String url;


    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "PersonRegisterBankTb{" +
        "bankId=" + bankId +
        ", accountId=" + accountId +
        ", name=" + name +
        ", bankName=" + bankName +
        ", cardId=" + cardId +
        ", cardName=" + cardName +
        ", province=" + province +
        ", city=" + city +
        ", tel=" + tel +
        "}";
    }
}
