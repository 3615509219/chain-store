package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-05-10
 */
public class HistoricalUserTb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("account_Code")
    private String accountCode;

    @TableField("account_ID")
    private Integer accountId;

    private String state;

    private String city;

    private String accountName;

    private String buyerPerson;

    private String buyerCompanyName;

    private String buyerCompanyTaxNumber;

    private String phoneNumber;

    private String emailAddr;

    private Integer accountPoints;

    private String personRegisterDate;

    private String callService;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBuyerPerson() {
        return buyerPerson;
    }

    public void setBuyerPerson(String buyerPerson) {
        this.buyerPerson = buyerPerson;
    }

    public String getBuyerCompanyName() {
        return buyerCompanyName;
    }

    public void setBuyerCompanyName(String buyerCompanyName) {
        this.buyerCompanyName = buyerCompanyName;
    }

    public String getBuyerCompanyTaxNumber() {
        return buyerCompanyTaxNumber;
    }

    public void setBuyerCompanyTaxNumber(String buyerCompanyTaxNumber) {
        this.buyerCompanyTaxNumber = buyerCompanyTaxNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public Integer getAccountPoints() {
        return accountPoints;
    }

    public void setAccountPoints(Integer accountPoints) {
        this.accountPoints = accountPoints;
    }

    public String getPersonRegisterDate() {
        return personRegisterDate;
    }

    public void setPersonRegisterDate(String personRegisterDate) {
        this.personRegisterDate = personRegisterDate;
    }

    public String getCallService() {
        return callService;
    }

    public void setCallService(String callService) {
        this.callService = callService;
    }

    @Override
    public String toString() {
        return "HistoricalUserTb{" +
        "id=" + id +
        ", accountCode=" + accountCode +
        ", accountId=" + accountId +
        ", state=" + state +
        ", city=" + city +
        ", accountName=" + accountName +
        ", buyerPerson=" + buyerPerson +
        ", buyerCompanyName=" + buyerCompanyName +
        ", buyerCompanyTaxNumber=" + buyerCompanyTaxNumber +
        ", phoneNumber=" + phoneNumber +
        ", emailAddr=" + emailAddr +
        ", accountPoints=" + accountPoints +
        ", personRegisterDate=" + personRegisterDate +
        ", callService=" + callService +
        "}";
    }
}
