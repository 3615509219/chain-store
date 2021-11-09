package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import com.lq.pojo.EvaluateImgTb;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-05-21
 */
@Data
public class EvaluateTb implements Serializable {


    private static final long serialVersionUID = -5245687246593333737L;
    @TableId(value = "evaluate_Id", type = IdType.AUTO)
    private Integer evaluateId;

    @TableField("account_Id")
    private Integer accountId;

    private String accountName;

    @TableField("purchase_order_product_pn_ID")
    private Integer purchaseOrderProductPnId;

    private String evaluate;

    private String time;

    private String txImg;

    private String lianQiReply;

    private String replyTime;

    private List<EvaluateImgTb> evaluateImgTb;

    private List<FinishedGoodsListTb> finishedGoodsListTb;
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<EvaluateImgTb> getEvaluateImgTb() {
        return evaluateImgTb;
    }

    public void setEvaluateImgTb(List<EvaluateImgTb> evaluateImgTb) {
        this.evaluateImgTb = evaluateImgTb;
    }

    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
        this.evaluateId = evaluateId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getPurchaseOrderProductPnId() {
        return purchaseOrderProductPnId;
    }

    public void setPurchaseOrderProductPnId(Integer purchaseOrderProductPnId) {
        this.purchaseOrderProductPnId = purchaseOrderProductPnId;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    @Override
    public String toString() {
        return "EvaluateTb{" +
        "evaluateId=" + evaluateId +
        ", accountId=" + accountId +
        ", purchaseOrderProductPnId=" + purchaseOrderProductPnId +
        ", evaluate=" + evaluate +
        "}";
    }
}
