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
 * @since 2021-05-17
 */
@Data
public class UserFeedback implements Serializable {


    private static final long serialVersionUID = -1497489697141399461L;
    @TableId(value = "feedback_Id", type = IdType.AUTO)
    private Integer feedbackId;

    @TableField("account_Id")
    private Integer accountId;

    private String feedbackUrl;

    private String feedback;

    private String proposal;

    private String bz;

    private String time;

    private String bzTime;


    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getFeedbackUrl() {
        return feedbackUrl;
    }

    public void setFeedbackUrl(String feedbackUrl) {
        this.feedbackUrl = feedbackUrl;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    @Override
    public String toString() {
        return "UserFeedback{" +
        "feedbackId=" + feedbackId +
        ", accountId=" + accountId +
        ", feedbackUrl=" + feedbackUrl +
        ", feedback=" + feedback +
        ", proposal=" + proposal +
        "}";
    }
}
