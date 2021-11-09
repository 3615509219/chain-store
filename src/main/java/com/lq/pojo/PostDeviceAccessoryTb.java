package com.lq.pojo;

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
//后处理设备和配件的中间表
public class PostDeviceAccessoryTb implements Serializable {

    private static final long serialVersionUID = -4918342274769896619L;
    @TableField("post_device_ID")
    private Integer postDeviceId;

    @TableField("post_device_accessory_PN")
    private String postDeviceAccessoryPn;


    public Integer getPostDeviceId() {
        return postDeviceId;
    }

    public void setPostDeviceId(Integer postDeviceId) {
        this.postDeviceId = postDeviceId;
    }

    public String getPostDeviceAccessoryPn() {
        return postDeviceAccessoryPn;
    }

    public void setPostDeviceAccessoryPn(String postDeviceAccessoryPn) {
        this.postDeviceAccessoryPn = postDeviceAccessoryPn;
    }

    @Override
    public String toString() {
        return "PostDeviceAccessoryTb{" +
        "postDeviceId=" + postDeviceId +
        ", postDeviceAccessoryPn=" + postDeviceAccessoryPn +
        "}";
    }
}
