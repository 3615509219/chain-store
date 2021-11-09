package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
//工厂信息表
public class ManufactureRegisterTb implements Serializable {

    private static final long serialVersionUID = -1929963799026938121L;
    private String manufactureCode;

    @TableId(value = "manufacture_ID", type = IdType.AUTO)
    private Integer manufactureId;

    private String manufactureState;

    private String manufactureCity;

    private String manufactureName;

    private String manufactureTax;

    private String manufactureWxName;

    private String manufactureContactWindow;

    private String manufactureContactWindowPhone;

    private String manufactureContactWindowEmail;

    private Integer manufacturePoints;

    private String manufactureBankNumber;

    private String manufactureBank;

    private String manufactureRegisterDate;

    private String manufactureOpenId;

    public String getManufactureOpenId() {
        return manufactureOpenId;
    }

    public void setManufactureOpenId(String manufactureOpenId) {
        this.manufactureOpenId = manufactureOpenId;
    }

    public String getManufactureCode() {
        return manufactureCode;
    }

    public void setManufactureCode(String manufactureCode) {
        this.manufactureCode = manufactureCode;
    }

    public Integer getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(Integer manufactureId) {
        this.manufactureId = manufactureId;
    }

    public String getManufactureState() {
        return manufactureState;
    }

    public void setManufactureState(String manufactureState) {
        this.manufactureState = manufactureState;
    }

    public String getManufactureCity() {
        return manufactureCity;
    }

    public void setManufactureCity(String manufactureCity) {
        this.manufactureCity = manufactureCity;
    }

    public String getManufactureName() {
        return manufactureName;
    }

    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }

    public String getManufactureTax() {
        return manufactureTax;
    }

    public void setManufactureTax(String manufactureTax) {
        this.manufactureTax = manufactureTax;
    }

    public String getManufactureWxName() {
        return manufactureWxName;
    }

    public void setManufactureWxName(String manufactureWxName) {
        this.manufactureWxName = manufactureWxName;
    }

    public String getManufactureContactWindow() {
        return manufactureContactWindow;
    }

    public void setManufactureContactWindow(String manufactureContactWindow) {
        this.manufactureContactWindow = manufactureContactWindow;
    }

    public String getManufactureContactWindowPhone() {
        return manufactureContactWindowPhone;
    }

    public void setManufactureContactWindowPhone(String manufactureContactWindowPhone) {
        this.manufactureContactWindowPhone = manufactureContactWindowPhone;
    }

    public String getManufactureContactWindowEmail() {
        return manufactureContactWindowEmail;
    }

    public void setManufactureContactWindowEmail(String manufactureContactWindowEmail) {
        this.manufactureContactWindowEmail = manufactureContactWindowEmail;
    }

    public Integer getManufacturePoints() {
        return manufacturePoints;
    }

    public void setManufacturePoints(Integer manufacturePoints) {
        this.manufacturePoints = manufacturePoints;
    }

    public String getManufactureBankNumber() {
        return manufactureBankNumber;
    }

    public void setManufactureBankNumber(String manufactureBankNumber) {
        this.manufactureBankNumber = manufactureBankNumber;
    }

    public String getManufactureBank() {
        return manufactureBank;
    }

    public void setManufactureBank(String manufactureBank) {
        this.manufactureBank = manufactureBank;
    }

    public String getManufactureRegisterDate() {
        return manufactureRegisterDate;
    }

    public void setManufactureRegisterDate(String manufactureRegisterDate) {
        this.manufactureRegisterDate = manufactureRegisterDate;
    }

    @Override
    public String toString() {
        return "ManufactureRegisterTb{" +
                "manufactureCode='" + manufactureCode + '\'' +
                ", manufactureId=" + manufactureId +
                ", manufactureState='" + manufactureState + '\'' +
                ", manufactureCity='" + manufactureCity + '\'' +
                ", manufactureName='" + manufactureName + '\'' +
                ", manufactureTax='" + manufactureTax + '\'' +
                ", manufactureWxName='" + manufactureWxName + '\'' +
                ", manufactureContactWindow='" + manufactureContactWindow + '\'' +
                ", manufactureContactWindowPhone='" + manufactureContactWindowPhone + '\'' +
                ", manufactureContactWindowEmail='" + manufactureContactWindowEmail + '\'' +
                ", manufacturePoints=" + manufacturePoints +
                ", manufactureBankNumber='" + manufactureBankNumber + '\'' +
                ", manufactureBank='" + manufactureBank + '\'' +
                ", manufactureRegisterDate=" + manufactureRegisterDate +
                ", manufactureOpenId='" + manufactureOpenId + '\'' +
                '}';
    }
}
