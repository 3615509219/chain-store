package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-06-25
 */
public class RefundImgTb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("refund_Id")
    private Integer refundId;

    private String refundImg;


    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public String getRefundImg() {
        return refundImg;
    }

    public void setRefundImg(String refundImg) {
        this.refundImg = refundImg;
    }

    @Override
    public String toString() {
        return "RefundImgTb{" +
        "refundId=" + refundId +
        ", refundImg=" + refundImg +
        "}";
    }
}
