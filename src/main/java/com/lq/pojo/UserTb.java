package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-04-22
 */
public class UserTb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_Id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPassword;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserTb{" +
        "userId=" + userId +
        ", userName=" + userName +
        ", userPassword=" + userPassword +
        "}";
    }
}
