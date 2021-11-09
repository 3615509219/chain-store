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
 * @since 2021-05-24
 */
@Data
public class EvaluateImgTb implements Serializable {


    private static final long serialVersionUID = -9171340071075643354L;
    @TableField("evaluate_Id")
    private Integer evaluateId;

    private String evaluateImg;

    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getEvaluateImg() {
        return evaluateImg;
    }

    public void setEvaluateImg(String evaluateImg) {
        this.evaluateImg = evaluateImg;
    }

    @Override
    public String toString() {
        return "EvaluateImgTb{" +
                "evaluateId=" + evaluateId +
                ", evaluateImg='" + evaluateImg + '\'' +
                '}';
    }
}
