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
public class PointsTb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "points_Id", type = IdType.AUTO)
    private Integer pointsId;

    @TableField("account_ID")
    private Integer accountId;

    private String name;

    private Integer points;

    private String bankName;

    @TableField("card_Id")
    private String cardId;

    private String cardName;

    private String province;

    private String city;

    private String tel;

    private String handle;

    private String time;

    private String completedTime;


    public Integer getPointsId() {
        return pointsId;
    }

    public void setPointsId(Integer pointsId) {
        this.pointsId = pointsId;
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
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
        return "PointsTb{" +
        "pointsId=" + pointsId +
        ", accountId=" + accountId +
        ", name=" + name +
        ", points=" + points +
        ", bankName=" + bankName +
        ", cardId=" + cardId +
        ", cardName=" + cardName +
        ", province=" + province +
        ", city=" + city +
        ", tel=" + tel +
        "}";
    }
}
